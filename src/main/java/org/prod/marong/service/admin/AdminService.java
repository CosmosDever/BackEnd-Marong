package org.prod.marong.service.admin;

import org.prod.marong.dto.AdminDetailDto;
import org.prod.marong.dto.AdminDto;
import org.prod.marong.model.entity.RoleEntity;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.RoleRepository;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;
    private final RoleRepository roleRepository;

    public AdminService(PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public List<AdminDto> getAllAdmins() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_master Admin") || role.getName().equals("ROLE_Admin")))
                .map(this::convertToAdminDto)
                .collect(Collectors.toList());
    }

    public AdminDetailDto getAdminById(Long id) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            return convertToAdminDetailDto(user);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }

    public AdminDetailDto updateAdmin(Long id, AdminDetailDto adminDetailDto) {
        Optional<UserEntity> userOpt = userRepository.findById(id);
        if (userOpt.isPresent()) {
            UserEntity user = userOpt.get();
            if (adminDetailDto.getFullName() != null) {
                user.setFullName(adminDetailDto.getFullName());
            }
            if (adminDetailDto.getPicture() != null) {
                user.setPicture(adminDetailDto.getPicture());
            }
            if (adminDetailDto.getPassword() != null) {
                user.setPassword(passwordEncoder.encode(adminDetailDto.getPassword()));
            }
            if (adminDetailDto.getBirthday() != null) {
                user.setBirthday(adminDetailDto.getBirthday());
            }
            if (adminDetailDto.getGender() != null) {
                user.setGender(adminDetailDto.getGender());
            }
            if (adminDetailDto.getTelephone() != null) {
                user.setTelephone(adminDetailDto.getTelephone());
            }
            if (adminDetailDto.getRole() != null) {
                Optional<RoleEntity> roleOpt = roleRepository.findByName("ROLE_" + adminDetailDto.getRole());
                if (roleOpt.isPresent()) {
                    user.setRoles(List.of(roleOpt.get()));
                } else {
                    throw new RuntimeException("Role not found: " + adminDetailDto.getRole());
                }
            }
            userRepository.save(user);
            return convertToAdminDetailDto(user);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }

    public void deleteAdmin(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("Admin not found with id: " + id);
        }
    }

    public AdminDetailDto createAdmin(AdminDetailDto adminDetailDto) {
        UserEntity user = new UserEntity();
        user.setFullName(adminDetailDto.getFullName());
        user.setGmail(adminDetailDto.getGmail());
        user.setPassword(passwordEncoder.encode(adminDetailDto.getPassword()));
        RoleEntity role = roleRepository.findByName("ROLE_" + adminDetailDto.getRole())
                .orElseThrow(() -> new RuntimeException("Role not found: " + adminDetailDto.getRole()));
        user.setRoles(List.of(role));
        user.setBirthday(adminDetailDto.getBirthday());
        user.setGender(adminDetailDto.getGender());
        user.setTelephone(adminDetailDto.getTelephone());
        userRepository.save(user);
        return convertToAdminDetailDto(user);
    }

   

    private AdminDto convertToAdminDto(UserEntity user) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(user.getId());
        adminDto.setPicture(user.getPicture());
        adminDto.setFullName(user.getFullName());
        adminDto.setRole(user.getRoles().stream().findFirst().get().getName());
        return adminDto;   
    }

    private AdminDetailDto convertToAdminDetailDto(UserEntity user) {
        AdminDetailDto adminDetailDto = new AdminDetailDto();
        adminDetailDto.setId(user.getId());
        adminDetailDto.setFullName(user.getFullName());
        adminDetailDto.setGmail(user.getGmail());
        adminDetailDto.setPassword(user.getPassword());
        adminDetailDto.setPicture(user.getPicture());
        adminDetailDto.setBirthday(user.getBirthday());
        adminDetailDto.setGender(user.getGender());
        adminDetailDto.setTelephone(user.getTelephone());
        adminDetailDto.setRole(user.getRoles().stream().findFirst().get().getName());
        return adminDetailDto;
    }


    
}
