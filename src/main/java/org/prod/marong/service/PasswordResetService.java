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
            PasswordResetToken resetToken = new PasswordResetToken(email, token, LocalDateTime.now().plusHours(1));
            tokenRepository.save(resetToken);

            String subject = "Password Reset";
            String htmlContent = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "    <style>" +
                    "        body { font-family: Arial, sans-serif; line-height: 1.6; color: #333; }" +
                    "        .container { padding: 20px; max-width: 600px; margin: 0 auto; border: 1px solid #ddd; border-radius: 8px; }" +
                    "        h1 { color: #4CAF50; }" +
                    "        p { margin: 10px 0; }" +
                    "        .token { font-size: 1.2em; font-weight: bold; color: #4CAF50; }" +
                    "    </style>" +
                    "</head>" +
                    "<body>" +
                    "    <div class='container'>" +
                    "        <h1>Password Reset</h1>" +
                    "        <p>Hello,</p>" +
                    "        <p>We received a request to reset your password. Please use the code below to proceed:</p>" +
                    "        <p class='token'>" + token + "</p>" +
                    "        <p>This code is valid for the next 1 hours. If you did not request a password reset, please ignore this email.</p>" +
                    "        <p>Thank you,</p>" +
                    "        <p>Marong Team</p>" +
                    "    </div>" +
                    "</body>" +
                    "</html>";

            emailService.sendEmail(email, subject, htmlContent, true); // Pass 'true' to indicate HTML content
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