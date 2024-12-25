package org.prod.marong.controller;

import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.TestService; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class TestController {

    private static final String SUCCESS = "200";
    // private static final String ERROR = "400";

    @Autowired
    private TestService testService;

    @SuppressWarnings("rawtypes")
    @GetMapping("/api/test")
    public ResponseEntity<ResponseModel> marongTest(){
        try {
            String testData = testService.getTestData();
            ResponseModel response = ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .data(testData)
                    .build();
            return ResponseEntity.ok(response);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
