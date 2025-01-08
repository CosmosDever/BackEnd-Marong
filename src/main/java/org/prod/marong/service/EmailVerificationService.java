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
import org.slf4j.LoggerFactory;
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
        EmailVerificationToken verificationToken = new EmailVerificationToken(email, token, LocalDateTime.now().plusHours(24));
        tokenRepository.save(verificationToken);

        String subject = "Email Verification";
        String text = "To verify your email, please use the following code: " + token;
        emailService.sendEmail(email, subject, text);
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