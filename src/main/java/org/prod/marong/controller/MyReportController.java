package org.prod.marong.controller;

import org.prod.marong.model.MyReportCaseModel;
import org.prod.marong.model.MyReportResponseModel;
import org.prod.marong.model.NewsModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.service.cases.CaseService;
import org.prod.marong.service.myreport.MyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class MyReportController {

    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    MyReportService myReportService;

    @GetMapping("/api/myreport/{id}/waiting")
    public ResponseModel getAllReportWaitingById(@PathVariable("id") String id){
        try {
            List<MyReportResponseModel> data = myReportService.getAllReportJoinCase(id,"waiting");
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(data)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }
    @GetMapping("/api/myreport/{id}/inprogress")
    public ResponseModel getAllReportInprogressById(@PathVariable("id") String id){
        try {
            List<MyReportResponseModel> data = myReportService.getAllReportJoinCase(id,"inprogress");
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(data)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }
    @GetMapping("/api/myreport/{id}/done")
    public ResponseModel getAllReportDoneById(@PathVariable("id") String id){
        try {
            List<MyReportResponseModel> data = myReportService.getAllReportJoinCase(id,"done");
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(data)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @GetMapping("/api/myreport/{id}/cancel")
    public ResponseModel getAllReportCancelById(@PathVariable("id") String id){
        try {
            List<MyReportResponseModel> data = myReportService.getAllReportJoinCase(id,"cancel");
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(data)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @GetMapping("/api/myreport/{id}/case/{caseid}")
    public ResponseModel getAllReportCancelById(@PathVariable("id") String id,@PathVariable("caseid") String caseid){
        try {
            MyReportCaseModel data = myReportService.getReportByUserIdCaseId(id,caseid);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(data)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }


}
