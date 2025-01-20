package org.prod.marong.controller;

import org.modelmapper.ModelMapper;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.UserModel;
import org.prod.marong.service.TestService;
import org.prod.marong.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
public class UserController {

    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    private TestService testService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserService userService;




    @GetMapping("/api/userdata/{id}")
    public ResponseModel getUserById(@PathVariable("id") String id){
        try {
            UserModel userData = userService.getUserData(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("User data retrieved successfully")
                    .data(userData)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving user data: " + e.getMessage())
                    .build();
        }
    }

    @GetMapping("/api/userdata/token/{token}")
    public ResponseModel getUserByToken(@PathVariable("token") String token){
        try {
            UserModel userData = userService.getUserDataByToken(token);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("User data retrieved successfully")
                    .data(userData)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving user data: " + e.getMessage())
                    .build();
        }
    }


    @GetMapping("/api/profile/{id}/info")
    public ResponseModel getProfileById(@PathVariable("id") String id){
        try {
            UserModel userData = userService.getUserData(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("User data retrieved successfully")
                    .data(userData)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving user data: " + e.getMessage())
                    .build();
        }
    }

    @PatchMapping("/api/profile/{id}/edit")
    public ResponseModel updateProfileById(@PathVariable("id") String id){
        try {
            UserModel userData = userService.getUserData(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("User data retrieved successfully")
                    .data(userData)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving user data: " + e.getMessage())
                    .build();
        }
    }

    

}
