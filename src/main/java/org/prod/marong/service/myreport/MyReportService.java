package org.prod.marong.service.myreport;

import org.prod.marong.model.CasesModel;
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

    public List<MyReportResponseModel> getAllReportJoinCase(String id,String status){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findReportJoinCaseById(id,status);

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
}
