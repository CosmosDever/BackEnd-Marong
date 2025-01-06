package org.prod.marong.controller;

import org.prod.marong.model.CasesModel;
import org.prod.marong.model.NewsModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.cases.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class CaseController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";


    @Autowired
    CaseService caseService;

    @GetMapping("/api/case/all")
    public ResponseModel getAllCase(){
        try {
            List<CasesModel> caseList = caseService.getAllCaseWithStatus();
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(caseList)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

}
