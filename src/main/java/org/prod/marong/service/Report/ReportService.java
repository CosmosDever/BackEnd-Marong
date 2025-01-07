package org.prod.marong.service.Report;

import org.prod.marong.model.ChangeStatusDoneResponseModel;
import org.prod.marong.model.ChangeStatusResponseModel;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.repository.CaseRepository;
import org.prod.marong.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportService {

    @Autowired
    ReportRepository reportRepository;
    @Autowired
    private CaseRepository caseRepository;

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

    public ChangeStatusDoneResponseModel updateCaseStatusDone(String id,String picture) {
        ReportEntity reportEntity = reportRepository.findReportById(id);
        CasesEntity casesEntity = caseRepository.findCaseById(id);

        if (reportEntity == null) {
            throw new IllegalArgumentException("Report with id " + id + " not found");
        }
        String LocalDate = String.valueOf(LocalDateTime.now());
        // Update the case status to "In Progress"
        reportEntity.setStatus("Done");
        reportEntity.setUpdatedAt(LocalDateTime.parse(LocalDate));
        ReportEntity report = reportRepository.save(reportEntity);
        casesEntity.setPicture_done(picture);
        CasesEntity casePicture = caseRepository.save(casesEntity);

        ChangeStatusDoneResponseModel reportModelResponse = new ChangeStatusDoneResponseModel();
        reportModelResponse.setCase_id(id);
        reportModelResponse.setStatus("Done");
        reportModelResponse.setDetail("ได้รับการแก้ไขโดยการซ่อมเรียบร้อย");
        reportModelResponse.setDate_updated(LocalDate);
        reportModelResponse.setPicture_done(picture);

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
