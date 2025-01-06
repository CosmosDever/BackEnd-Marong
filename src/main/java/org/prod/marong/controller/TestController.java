package org.prod.marong.controller;

import org.modelmapper.ModelMapper;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.TestModel;
import org.prod.marong.model.UserModel;
import org.prod.marong.service.TestService;
import org.prod.marong.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TestController {

    private static final String SUCCESS = "200";
     private static final String ERROR = "400";

    @Autowired
    private TestService testService;

    @Autowired
    ModelMapper mapper;

    @Autowired
    UserService userService;


    @SuppressWarnings("rawtypes")
//    @GetMapping("/api/test")
//    public Object marongTest(){
//        try {
//            String testData = testService.getTestData();
//            ResponseModel response = ResponseModel.builder()
//                    .statusCode(SUCCESS)
//                    .statusMessage("Successful Operation")
//                    .data(testData)
//                    .build();
//            return response;
//        } catch (Exception e) {
//            ResponseModel response = ResponseModel.builder()
//                    .statusCode(ERROR)
//                    .statusMessage("Error Occurred : " + e.getMessage())
//                    .build();
//            return response;
//        }
//    }

//    @GetMapping("test")
//    public String test(){
//        String testData = testService.getTestData();
//        TestModel response = new TestModel();
//        response.setData(testData);
//        return mapper.map(response, String.class);
//
//    }

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

}
