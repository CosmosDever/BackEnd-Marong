package org.prod.marong.service.myreport;

import org.prod.marong.model.CasesModel;
import org.prod.marong.model.MyReportCaseModel;
import org.prod.marong.model.MyReportResponseModel;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.repository.ReportJoinCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MyReportService {

    @Autowired
    ReportJoinCaseRepository reportJoinCaseRepository;

    public List<MyReportResponseModel> getAllReportJoinCase(String id, String status) {
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findReportJoinCaseById(id, status);

        List<MyReportResponseModel> myReportList = allCases.stream().map(entity -> {
            MyReportResponseModel model = new MyReportResponseModel();
            model.setCase_id(id);
            model.setType_of_issue(entity.getDetailDetect());
            model.setDate_report(String.valueOf(entity.getUpdatedAt()));
            model.setStatus(entity.getStatus());
            model.setLocation(entity.getCases().getLocation());
            return model;
        }).collect(Collectors.toList());


        return myReportList;
    }

    public MyReportCaseModel getReportByUserIdCaseId(String id, String caseid) {
        ReportJoinCaseEntity caseById = reportJoinCaseRepository.findReportByUserIdCaseID(id, caseid);
//        ReportJoinCaseEntity caseById = reportJoinCaseRepository.findReportJoinCaseEntityByUserIdAndCaseId(id,caseid);

        MyReportCaseModel model = new MyReportCaseModel();
        model.setId(String.valueOf(caseById.getId()));
        model.setType_of_issue(caseById.getCategory());
        model.setLocation(caseById.getCases().getLocation());
        model.setPicture(caseById.getCases().getPicture());
        model.setDetail(caseById.getDetailDetect());
        model.setDamage_value(caseById.getDamageValue());
        model.setDate_opened(String.valueOf(caseById.getCases().getDate_opened()));
        model.setDate_closed(String.valueOf(caseById.getCases().getDate_closed()));
        model.setStatus(caseById.getStatus());
        model.setUserId(String.valueOf(caseById.getUserId()));
        return model;


    }
}