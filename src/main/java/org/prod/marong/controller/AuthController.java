package org.prod.marong.controller;

import org.prod.marong.dto.UserRegistrationDto;
import org.prod.marong.model.entity.RoleEntity;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.RoleRepository;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
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

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public String registerUser(@RequestBody UserRegistrationDto userDto) {
        if (userRepository.existsByGmail(userDto.getGmail())) {
            return "Email is already in use!";
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

        return "User registered successfully!";
    }
}
