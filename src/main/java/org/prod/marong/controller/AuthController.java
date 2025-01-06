package org.prod.marong.controller;

import org.prod.marong.dto.AuthResponseDTO;
import org.prod.marong.dto.LoginDto;
import org.prod.marong.dto.UserRegistrationDto;
import org.prod.marong.model.entity.RoleEntity;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.RoleRepository;
import org.prod.marong.repository.UserRepository;
import org.prod.marong.security.JWTGenerator;
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
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = new JWTGenerator();
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginDto.getGmail(),
                            loginDto.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            String token = jwtGenerator.generateToken(authentication);
            
            return new ResponseEntity<>(Collections.singletonMap("data", Collections.singletonMap("token", Collections.singletonList(token))), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Invalid login credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> registerUser(@RequestBody UserRegistrationDto userDto) {
        try {
            if (userRepository.existsByGmail(userDto.getGmail())) {
                return new ResponseEntity<>("Gmail is taken!", HttpStatus.BAD_REQUEST);
            }

            // Create a new UserEntity
            UserEntity user = new UserEntity();
            user.setFullName(userDto.getFullName());
            user.setGmail(userDto.getGmail());
            user.setPassword(passwordEncoder.encode(userDto.getPassword()));
            user.setTelephone(userDto.getTelephone());
            user.setGender(userDto.getGender());
            user.setBirthday(userDto.getBirthday());

            // Assign roles
            RoleEntity userRole = roleRepository.findByName("User")
                    .orElseThrow(() -> new RuntimeException("Role not found"));
            user.setRoles(Collections.singletonList(userRole));

            // Save the user
            UserEntity savedUser = userRepository.save(user);

            // Create response
            Map<String, Object> responseData = new HashMap<>();
            responseData.put("id", savedUser.getId());
            responseData.put("full_name", savedUser.getFullName());
            responseData.put("email", savedUser.getGmail());
            responseData.put("phone", savedUser.getTelephone());
            responseData.put("gender", savedUser.getGender());
            responseData.put("birthday", savedUser.getBirthday());

            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("message", "User account created successfully.");
            response.put("data", responseData);

            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("An error occurred during registration", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
