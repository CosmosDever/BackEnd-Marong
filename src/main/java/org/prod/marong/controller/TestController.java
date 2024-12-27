package org.prod.marong.controller;

import org.modelmapper.ModelMapper;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.TestModel;
import org.prod.marong.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
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


    @SuppressWarnings("rawtypes")
    @GetMapping("/api/test")
    public Object marongTest(){
        try {
            String testData = testService.getTestData();
            ResponseModel response = new ResponseModel();
            response.setStatusCode(SUCCESS);
            response.setData(testData);
            return response;
        } catch (Exception e) {
            ResponseModel response = new ResponseModel();
            response.setStatusCode(ERROR);
            return response;
        }
    }

    @GetMapping("test")
    public String test(){
        String testData = testService.getTestData();
        TestModel response = new TestModel();
        response.setData(testData);
        return mapper.map(response, String.class);

    }

}
