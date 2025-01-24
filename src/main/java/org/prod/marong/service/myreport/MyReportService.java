package org.prod.marong.service.myreport;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.repository.CaseRepository;
import org.prod.marong.repository.ReportJoinCaseRepository;
import org.prod.marong.repository.ReportJoinCaseUserRepository;
import org.prod.marong.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyReportService {

    @Autowired
    ReportJoinCaseRepository reportJoinCaseRepository;
    @Autowired
    private CaseRepository caseRepository;
    @Autowired
    private ReportRepository reportRepository;

    public List<MyReportResponseModel> getAllReportJoinCase(String id, String status) {
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findReportJoinCaseById(id, status);

        List<MyReportResponseModel> myReportList = allCases.stream().map(entity -> {
            MyReportResponseModel model = new MyReportResponseModel();
            LocationModel location = new LocationModel();
            String[] coordinates = new String[2];
            coordinates[0] = entity.getCases().getLatitude();
            coordinates[1] = entity.getCases().getLongitude();
            location.setCoordinates(coordinates);
            model.setCase_id(id);
            model.setType_of_issue(entity.getDetailDetect());
            model.setDate_report(String.valueOf(entity.getUpdatedAt()));
            model.setStatus(entity.getStatus());
            model.setLocation(location);
            return model;
        }).collect(Collectors.toList());


        return myReportList;
    }

    public MyReportCaseModel getReportByUserIdCaseId(String id, String caseid) {
        ReportJoinCaseEntity caseById = reportJoinCaseRepository.findReportByUserIdCaseID(id, caseid);
//        ReportJoinCaseEntity caseById = reportJoinCaseRepository.findReportJoinCaseEntityByUserIdAndCaseId(id,caseid);

        MyReportCaseModel model = new MyReportCaseModel();
        LocationModel location = new LocationModel();
        String[] coordinates = new String[2];
        coordinates[0] = caseById.getCases().getLatitude();
        coordinates[1] = caseById.getCases().getLongitude();
        location.setCoordinates(coordinates);
        model.setId(String.valueOf(caseById.getId()));
        model.setType_of_issue(caseById.getCategory());
        model.setLocation(location);
        model.setPicture(caseById.getCases().getPicture());
        model.setDetail(caseById.getDetailDetect());
        model.setDamage_value(caseById.getDamageValue());
        model.setDate_opened(String.valueOf(caseById.getCases().getDate_opened()));
        model.setDate_closed(String.valueOf(caseById.getCases().getDate_closed()));
        model.setStatus(caseById.getStatus());
        model.setUserId(String.valueOf(caseById.getUserId()));
        return model;


    }

    public ReportCaseResponseModel createCase(String id, String category, String latitude, String longitude, String location_detail, String detail, String picture) {
        LocationModel location = new LocationModel();
        String[] coordinates = new String[2];
        coordinates[0] = latitude;
        coordinates[1] = longitude;
        location.setCoordinates(coordinates);
        location.setDescription(location_detail);
        CasesEntity newCase = new CasesEntity();
        newCase.setCategory(category);
        newCase.setDetail(detail);
        newCase.setPicture(picture);
        newCase.setLocation_description(location_detail);
        newCase.setLatitude(latitude);
        newCase.setLongitude(longitude);
        newCase.setDate_opened(LocalDate.now());



        // Save the case entity
        CasesEntity savedCase = caseRepository.save(newCase);

        // Optionally, you can also create a report for this case
        ReportEntity newReport = new ReportEntity();
        newReport.setStatus("Waiting");
        newReport.setCategory(category);
        newReport.setCaseId(Math.toIntExact(savedCase.getId()));
        newReport.setUserId(Integer.valueOf(id));
        newReport.setCreatedAt(LocalDateTime.now());
        System.out.println(newCase);
        System.out.println(newReport);
        ReportEntity report = reportRepository.save(newReport);



        ReportCaseResponseModel response = new ReportCaseResponseModel();
        response.setCaseId(1L);
        response.setDetail(detail);
        response.setDate_opened(String.valueOf(LocalDate.now()));
        response.setPicture(picture);
        response.setCategory(category);
        response.setLocation(location);
        response.setDetail(detail);
        response.setStatus("Waiting");
        return response;
    }

}
