package org.prod.marong.controller;

import org.prod.marong.dto.AuthResponseDTO;
import org.prod.marong.dto.LoginDto;
import org.prod.marong.dto.UserRegistrationDto;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.UserRepository;
import org.prod.marong.security.JWTGenerator;
import org.prod.marong.service.EmailVerificationService;
import org.prod.marong.service.PasswordResetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTGenerator jwtGenerator;

    @Autowired
    private EmailVerificationService emailVerificationService;

     @Autowired
    private PasswordResetService passwordResetService;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getGmail(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", Collections.singletonMap("token", Collections.singletonList(token)));
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "Invalid login credentials");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegistrationDto userRegistrationDto) {
        try {
            if (userRepository.existsByGmail(userRegistrationDto.getGmail())) {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Email is already in use");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }

            // Initiate email verification
            emailVerificationService.initiateEmailVerification(userRegistrationDto.getGmail());

            // Temporarily save user details in a map or a temporary storage
            emailVerificationService.saveUserDetails(userRegistrationDto);

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "Verification email sent");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "An error occurred during registration");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/verify/email")
    public ResponseEntity<Object> verifyEmail(@RequestParam String token) {
        try {
            boolean isVerified = emailVerificationService.verifyEmail(token);
            if (isVerified) {
                // Save the user after email verification
                UserEntity savedUser = emailVerificationService.saveUserAfterVerification(token);
                Map<String, Object> response = new HashMap<>();
                response.put("status", "success");
                response.put("message", "Email verified successfully and user registered");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                Map<String, Object> response = new HashMap<>();
                response.put("status", "error");
                response.put("message", "Invalid or expired verification token");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "An error occurred during email verification");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/verify/email/{email}/status")
    public ResponseEntity<Object> checkEmailVerificationStatus(@PathVariable String email) {
        try {
            boolean isVerified = emailVerificationService.isEmailVerified(email);
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", isVerified ? "verified" : "not verified");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, Object> response = new HashMap<>();
            response.put("status", "error");
            response.put("message", "An error occurred while checking email verification status");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping("/password/change/initiate")
    public ResponseEntity<Object> initiatePasswordReset(@RequestBody Map<String, String> request) {
        String email = request.get("gmail");
        passwordResetService.initiatePasswordReset(email);
        return new ResponseEntity<>(Map.of("status", "success", "message", "Password reset instructions sent to your email."), HttpStatus.OK);
    }

    @PostMapping("/password/change/verify")
    public ResponseEntity<Object> verifyResetCode(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        String resetCode = request.get("reset_code");
        boolean isValid = passwordResetService.verifyResetCode(email, resetCode);
        if (isValid) {
            return new ResponseEntity<>(Map.of("status", "success", "message", "Password reset code is valid. Proceed to change your password."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("status", "error", "message", "Invalid reset code or expired code."), HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/password/change/{email}")
    public ResponseEntity<Object> changePassword(@PathVariable String email, @RequestBody Map<String, String> request) {
        String newPassword = request.get("new_password");
        String confirmPassword = request.get("confirm_password");
        boolean isChanged = passwordResetService.changePassword(email, newPassword, confirmPassword);
        if (isChanged) {
            return new ResponseEntity<>(Map.of("status", "success", "message", "Password changed successfully."), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(Map.of("status", "error", "message", "Password change failed. Passwords do not match or something went wrong."), HttpStatus.BAD_REQUEST);
        }
    }
}
