package org.prod.marong.controller;

import org.apache.coyote.Response;
import org.prod.marong.model.CasesModel;
import org.prod.marong.model.ChangeStatusResponseModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.Report.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ReportController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    ReportService reportService;

    @PostMapping("/api/case/{id}/changeStatus/inprogress")
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


}