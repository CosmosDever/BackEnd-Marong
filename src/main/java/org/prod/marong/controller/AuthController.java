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
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getGmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return new ResponseEntity<>(new AuthResponseDTO(token), HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody UserRegistrationDto userDto) {
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
        userRepository.save(user);

        return new ResponseEntity<>("Sign up success!", HttpStatus.OK);
    }
}
