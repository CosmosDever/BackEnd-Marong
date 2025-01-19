package org.prod.marong.service.user;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.User;
import org.prod.marong.model.UserModel;
import org.prod.marong.model.UserModel2;
import org.prod.marong.model.entity.UserEntity;
import org.prod.marong.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.prod.marong.model.entity.RoleEntity;

import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

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


    public UserModel2 getProfile(String userId) {

        UserEntity userData = userRepository.findByUserId(userId);
        UserModel2 userModel = new UserModel2();
        userModel.setId(userData.getId());
        userModel.setFullName(userData.getFullName());
        userModel.setGmail(userData.getGmail());
        userModel.setBirthday(String.valueOf(userData.getBirthday()));
        userModel.setGender(userData.getGender());
        userModel.setPicture(userData.getPicture());

        
        return userModel;
    }

    public UserModel2 updateProfile(String userId,String fullname,String birthday,String gender,String picture) {

        UserEntity userData = userRepository.findByUserId(userId);
        if (userData == null) {
            return null;
        }

        if (fullname != null) {
            userData.setFullName(fullname);
        }

        if (birthday != null) {
            userData.setBirthday(LocalDate.parse(birthday));
        }
        if (gender != null) {
            userData.setGender(gender);
        }
        if (picture != null) {
            userData.setPicture(picture);
        }
       
        userRepository.save(userData);
        UserModel2 model = new UserModel2();
        model.setId(Long.valueOf(userId));
        model.setFullName(fullname);
        model.setBirthday(birthday);
        model.setGender(gender);
        model.setPicture(picture);
                
        return model;
    }





}
