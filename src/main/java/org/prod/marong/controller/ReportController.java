package org.prod.marong.controller;

import org.apache.coyote.Response;
import org.prod.marong.model.CasesModel;
import org.prod.marong.model.ChangeStatusDoneResponseModel;
import org.prod.marong.model.ChangeStatusResponseModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.Report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
public class ReportController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    ReportService reportService;

    @PostMapping("/api/case/{id}/changeStatus/inprogress")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseModel changeInprogress(@PathVariable("id") String id){
        try {
            ChangeStatusResponseModel response = reportService.updateCaseStatusInprogress(id);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @PostMapping("/api/case/{id}/changeStatus/cancelCase")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")

    public ResponseModel changeCancelCase(@PathVariable("id") String id,
                                          @RequestParam("detail") String detail){
        try {
            ChangeStatusResponseModel response = reportService.updateCaseStatusCancel(id,detail);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }

    @PostMapping("/api/case/{id}/changeStatus/done")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")

    public ResponseModel changeDone(@PathVariable("id") String id,
                                          @RequestParam("picture") String picture){
        try {
            ChangeStatusDoneResponseModel response = reportService.updateCaseStatusDone(id,picture);
            return ResponseModel.builder()
                    .statusCode(SUCCESS)
                    .statusMessage("All data retrieved successfully")
                    .data(response)
                    .build();
        } catch (Exception e) {
            return ResponseModel.builder()
                    .statusCode(ERROR)
                    .statusMessage("Error retrieving data: " + e.getMessage())
                    .build();
        }
    }


}
