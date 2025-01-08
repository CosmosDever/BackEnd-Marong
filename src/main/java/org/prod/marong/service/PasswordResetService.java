package org.prod.marong.service;

import org.prod.marong.model.entity.PasswordResetToken;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.PasswordResetTokenRepository;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;
import java.util.UUID;

@Service
public class PasswordResetService {

    @Autowired
    private PasswordResetTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public void initiatePasswordReset(String email) {
        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByGmail(email));
        if (userOpt.isPresent()) {
            String token = String.format("%06d", new Random().nextInt(999999));
            PasswordResetToken resetToken = new PasswordResetToken(email, token, LocalDateTime.now().plusHours(4));
            tokenRepository.save(resetToken);

            String subject = "Password Reset";
            String text = "To reset your password, please use the following code: " + token;
            emailService.sendEmail(email, subject, text);
        }
    }

    public boolean verifyResetCode(String email, String token) {
        Optional<PasswordResetToken> resetTokenOpt = tokenRepository.findByEmailAndToken(email, token);
        if (resetTokenOpt.isPresent()) {
            PasswordResetToken resetToken = resetTokenOpt.get();
            if (resetToken.getExpiresAt().isAfter(LocalDateTime.now())) {
                tokenRepository.delete(resetToken);
                return true;
            }
        }
        return false;
    }

    public boolean changePassword(String email, String newPassword, String confirmPassword) {
        if (!newPassword.equals(confirmPassword)) {
            return false;
        }

        Optional<UserEntity> userOpt = Optional.ofNullable(userRepository.findByGmail(email));
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            user.setPassword(passwordEncoder.encode(newPassword));
            userRepository.save(user);
            return true;
        }
        return false;
    }

    private void sendEmail(String to, String subject, String text) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(text);
        mailSender.send(mailMessage);
    }
}