package org.prod.marong.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.prod.marong.dto.UserRegistrationDto;
import org.prod.marong.model.entity.EmailVerificationToken;
import org.prod.marong.model.entity.RoleEntity;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.EmailVerificationTokenRepository;
import org.prod.marong.repository.RoleRepository;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class EmailVerificationService {

    @Autowired
    private EmailVerificationTokenRepository tokenRepository;

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private EmailService emailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;
    private Map<String, UserRegistrationDto> temporaryUserStorage = new HashMap<>();
    private static final Logger logger = LoggerFactory.getLogger(EmailVerificationService.class);

    public void initiateEmailVerification(String email) {
        String token = String.format("%06d", new Random().nextInt(999999));
        EmailVerificationToken verificationToken = new EmailVerificationToken(email, token, LocalDateTime.now().plusHours(1));
        tokenRepository.save(verificationToken);

        String subject = "Email Verification";
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
                "        <h1>Email Verification</h1>" +
                "        <p>Hello,</p>" +
                "        <p>Thank you for registering. To complete your email verification, please use the code below:</p>" +
                "        <p class='token'>" + token + "</p>" +
                "        <p>This code is valid for the next 1 hours. If you did not register, please ignore this email.</p>" +
                "        <p>Thank you,</p>" +
                "        <p>Marong Team</p>" +
                "    </div>" +
                "</body>" +
                "</html>";

        emailService.sendEmail(email, subject, htmlContent, true); // Pass 'true' to indicate HTML content
    }


    public boolean verifyEmail(String token) {
        Optional<EmailVerificationToken> verificationTokenOpt = tokenRepository.findByToken(token);
        if (verificationTokenOpt.isPresent()) {
            EmailVerificationToken verificationToken = verificationTokenOpt.get();
            if (verificationToken.getExpiresAt().isAfter(LocalDateTime.now())) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmailVerified(String email) {
        return !tokenRepository.existsByEmail(email);
    }
    public void saveUserDetails(UserRegistrationDto userRegistrationDto) {
        temporaryUserStorage.put(userRegistrationDto.getGmail(), userRegistrationDto);
        logger.info("User details saved for email: {}", userRegistrationDto.getGmail());
    }

    public UserEntity saveUserAfterVerification(String token) {
        Optional<EmailVerificationToken> verificationTokenOpt = tokenRepository.findByToken(token);
        if (verificationTokenOpt.isPresent()) {
            EmailVerificationToken verificationToken = verificationTokenOpt.get();
            String email = verificationToken.getEmail();
            UserRegistrationDto userRegistrationDto = temporaryUserStorage.get(email);

            if (userRegistrationDto == null) {
                throw new RuntimeException("User details not found for email: " + email);
            }

            UserEntity user = new UserEntity();
            user.setFullName(userRegistrationDto.getFullName());
            user.setGmail(userRegistrationDto.getGmail());
            user.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
            user.setBirthday(userRegistrationDto.getBirthday());
            user.setGender(userRegistrationDto.getGender());
            user.setTelephone(userRegistrationDto.getTelephone());
            user.setEmailVerified(true);

            RoleEntity role = roleRepository.findByName("ROLE_User")
                    .orElseThrow(() -> new RuntimeException("Role not found: ROLE_User"));
            user.setRoles(List.of(role));
            tokenRepository.delete(verificationToken);
            temporaryUserStorage.remove(email); // Remove user details after saving
            return userRepository.save(user);
        }
        throw new RuntimeException("Invalid token");
    }
}