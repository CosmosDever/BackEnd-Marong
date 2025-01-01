package org.prod.marong.controller;

import org.prod.marong.model.ChangeStatusResponseModel;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class ReportController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

    @Autowired
    ReportRepository reportRepository;

    public ChangeStatusResponseModel updateCaseStatusInprogress(String id) {
        ReportEntity reportEntity = reportRepository.findReportById(id);

        if (reportEntity == null) {
            throw new IllegalArgumentException("Report with id " + id + " not found");
        }
        String LocalDate = String.valueOf(LocalDateTime.now());
        // Update the case status to "In Progress"
        reportEntity.setStatus("In progress");
        reportEntity.setUpdatedAt(LocalDateTime.parse(LocalDate));
        ReportEntity report = reportRepository.save(reportEntity);

        ChangeStatusResponseModel reportModelResponse = new ChangeStatusResponseModel();
        reportModelResponse.setCase_id(report.getCaseId().toString());
        reportModelResponse.setStatus("In progress");
        reportModelResponse.setDetail("กำลังส่งเรื่องให้หน่วยงานที่เกี่ยวข้องเพื่อทำการแก้ไขครับ");
        reportModelResponse.setDate_updated(LocalDate);

        return reportModelResponse;



    }

    public ChangeStatusResponseModel updateCaseStatusDone(String id,String picture) {
        ReportEntity reportEntity = reportRepository.findReportById(id);

        if (reportEntity == null) {
            throw new IllegalArgumentException("Report with id " + id + " not found");
        }
        String LocalDate = String.valueOf(LocalDateTime.now());
        // Update the case status to "In Progress"
        reportEntity.setStatus("Done");
        reportEntity.setUpdatedAt(LocalDateTime.parse(LocalDate));
        ReportEntity report = reportRepository.save(reportEntity);

        // waiting save image ?

        ChangeStatusResponseModel reportModelResponse = new ChangeStatusResponseModel();
        reportModelResponse.setCase_id(report.getCaseId().toString());
        reportModelResponse.setStatus("Done");
        reportModelResponse.setDetail("ได้รับการแก้ไขโดยการซ่อมเรียบร้อย");
        reportModelResponse.setDate_updated(LocalDate);

        return reportModelResponse;



    }

    public ChangeStatusResponseModel updateCaseStatusCancel(String id,String detail) {
        ReportEntity reportEntity = reportRepository.findReportById(id);

        if (reportEntity == null) {
            throw new IllegalArgumentException("Report with id " + id + " not found");
        }
        String LocalDate = String.valueOf(LocalDateTime.now());
        reportEntity.setStatus("Cancelled");
        reportEntity.setUpdatedAt(LocalDateTime.parse(LocalDate));
        reportEntity.setDetailDetect(detail);
        ReportEntity report = reportRepository.save(reportEntity);

        ChangeStatusResponseModel reportModelResponse = new ChangeStatusResponseModel();
        reportModelResponse.setCase_id(report.getCaseId().toString());
        reportModelResponse.setStatus("Cancelled");
        reportModelResponse.setDetail(detail);
        reportModelResponse.setDate_updated(LocalDate);

        return reportModelResponse;



    }
}
