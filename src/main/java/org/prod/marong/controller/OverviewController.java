package org.prod.marong.controller;

import org.prod.marong.model.ChangeStatusResponseModel;
import org.prod.marong.model.OverviewModel;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.service.overview.OverviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class OverviewController {

    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    OverviewService overviewService;

    @GetMapping("/api/overview/all")
    public ResponseModel AllOverview(){
        try {
            OverviewModel response = overviewService.getAllOverview();
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

    @GetMapping("/api/overview/road")
    public ResponseModel AllOverviewRoad(){
        try {
            OverviewModel response = overviewService.getAllOverviewRoad();
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

    @GetMapping("/api/overview/wire")
    public ResponseModel AllOverviewWire(){
        try {
            OverviewModel response = overviewService.getAllOverviewWire();
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

    @GetMapping("/api/overview/pavement")
    public ResponseModel AllOverviewPavement(){
        try {
            OverviewModel response = overviewService.getAllOverviewPavement();
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

    @GetMapping("/api/overview/overpass")
    public ResponseModel OVAllOverviewOverpass(){
        try {
            OverviewModel response = overviewService.getAllOverviewOverpass();
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


    @GetMapping("/api/overview/test")
    public ResponseModel AllOverviewTest(){
        try {
            List<ReportJoinCaseEntity> response = overviewService.getAllOverviewTest();
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
