package org.prod.marong.controller;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.service.cases.CaseService;
import org.prod.marong.service.myreport.MyReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
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

    @PostMapping("/api/myreport/{id}/addCase")
    public ResponseModel createCase(@PathVariable("id") String id,
                               @RequestParam("category") String category,
                               @RequestParam("latitude") String latitude,
                               @RequestParam("longitude") String longitude,
                               @RequestParam("picture") String picture,
                               @RequestParam("detail") String detail,
                                    @RequestParam("location_detail") String location_detail) {
        try {
            ReportCaseResponseModel data = myReportService.createCase(id,category,latitude,location_detail,longitude,picture,detail)
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("New case added successfully.")
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
