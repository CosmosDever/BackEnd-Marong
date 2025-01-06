package org.prod.marong.service.admin;

import org.prod.marong.dto.AdminDto;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdminService {

    @Autowired
    private UserRepository userRepository;

    public List<AdminDto> getAllAdmins() {
        return userRepository.findAll().stream()
                .filter(user -> user.getRoles().stream().anyMatch(role -> role.getName().equals("ROLE_master Admin") || role.getName().equals("ROLE_Admin")))
                .map(this::convertToAdminDto)
                .collect(Collectors.toList());
    }

    private AdminDto convertToAdminDto(UserEntity user) {
        AdminDto adminDto = new AdminDto();
        adminDto.setId(user.getId());
        adminDto.setPicture(user.getPicture());
        adminDto.setFullName(user.getFullName());
        adminDto.setRole(user.getRoles().stream().findFirst().get().getName());
        return adminDto;
    }
}
