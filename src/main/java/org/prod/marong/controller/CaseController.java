package org.prod.marong.controller;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.ReportJoinCaseUserEntity;
import org.prod.marong.service.cases.CaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class CaseController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";


    @Autowired
    CaseService caseService;

    @GetMapping("/api/case/all")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel getAllCase(){
        try {
            List<AllCasesModel> caseList = caseService.getAllCase();
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

    @GetMapping("/api/case/{id}")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel getCaseById(@PathVariable("id") String id){
        try {
            CasesByIdModel caseById = caseService.getCaseById(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(caseById)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

}
