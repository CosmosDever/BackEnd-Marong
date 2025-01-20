package org.prod.marong.service.user;

import lombok.extern.slf4j.Slf4j;
import org.prod.marong.model.UserModel;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.UserRepository;
import org.prod.marong.security.CustomUserDetailsService;
import org.prod.marong.security.JWTGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.prod.marong.model.entity.RoleEntity;


import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JWTGenerator tokenGenerator;

    public UserModel getUserData(String userId) {

        UserEntity userData = userRepository.findByUserId(userId);
        UserModel userModel = new UserModel();
        userModel.setId(userData.getId());
        userModel.setFullName(userData.getFullName());
        userModel.setGmail(userData.getGmail());
        userModel.setBirthday(String.valueOf(userData.getBirthday()));
        userModel.setGender(userData.getGender());
        userModel.setPicture(userData.getPicture());
        userModel.setRoles(userData.getRoles().stream()
            .map(RoleEntity::toString)
            .collect(Collectors.joining(", ")));
        return userModel;
        
    }


    public UserModel getProfile(String userId) {

        UserEntity userData = userRepository.findByUserId(userId);
        UserModel userModel = new UserModel();
        userModel.setId(userData.getId());
        userModel.setFullName(userData.getFullName());
        userModel.setGmail(userData.getGmail());
        userModel.setBirthday(String.valueOf(userData.getBirthday()));
        userModel.setGender(userData.getGender());
        userModel.setPicture(userData.getPicture());
        userModel.setRoles(userData.getRoles().stream()
                .map(RoleEntity::toString)
                .collect(Collectors.joining(", ")));
        return userModel;
    }
    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    public UserModel getUserDataByToken(String token) {

        String gmail = tokenGenerator.getUsernameFromJWT(token);
        log.info("Gmail extracted from token: " + gmail);
        UserEntity userData = userRepository.findByGmail(gmail);
        UserModel userModel = new UserModel();
        userModel.setId(userData.getId());
        userModel.setFullName(userData.getFullName());
        userModel.setGmail(userData.getGmail());
        userModel.setBirthday(String.valueOf(userData.getBirthday()));
        userModel.setGender(userData.getGender());
        userModel.setPicture(userData.getPicture());
        userModel.setRoles(userData.getRoles().stream()
                .map(RoleEntity::toString)
                .collect(Collectors.joining(", ")));
        return userModel;
    }
     
    }

    




