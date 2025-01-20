package org.prod.marong.service.cases;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.model.entity.ReportJoinCaseUserEntity;
import org.prod.marong.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaseService {
    @Autowired
    CaseRepository caseRepository;
    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ReportJoinCaseUserRepository reportJoinCaseUserRepository;
    @Autowired
    private ReportJoinCaseRepository reportJoinCaseRepository;

    public List<CasesModel> getAllCaseWithStatus() {
        List<CasesEntity> allCases = caseRepository.findAllCase();

        List<CasesModel> casesData = allCases.stream().map(entity -> {
            CasesModel model = new CasesModel();
            LocationModel location = new LocationModel();
            String[] coordinates = new String[2];
            coordinates[0] = entity.getLatitude();
            coordinates[1] = entity.getLongitude();
            location.setCoordinates(coordinates);
            // do you have another solve? 
            String statusById = reportRepository.findReportStatusById(String.valueOf(entity.getId()));
            model.setCaseId(entity.getId());
            model.setPicture(entity.getPicture());
            model.setDetail(entity.getDetail());
            model.setCategory(entity.getCategory());
            model.setLocation(location);
            model.setDateOpened(entity.getDate_opened());
            model.setReportStatus(statusById);
            return model;
        }).collect(Collectors.toList());

        return casesData;
    }

    public CasesByIdModel getCaseById(String id) {
        ReportJoinCaseUserEntity caseById = reportJoinCaseUserRepository.findReportJoinCaseUserById(id);
        CasesByIdModel model = new CasesByIdModel();
        UserCaseModel userModel = new UserCaseModel();
        LocationModel location = new LocationModel();
        userModel.setUser_id(String.valueOf(caseById.getUser().getId()));
        userModel.setGmail(caseById.getUser().getGmail());
        userModel.setFull_name(caseById.getUser().getFullName());
        userModel.setPicture(caseById.getUser().getPicture());
        String[] coordinates = new String[2];
        coordinates[0] = caseById.getCases().getLatitude();
        coordinates[1] = caseById.getCases().getLongitude();
        location.setCoordinates(coordinates);
        model.setCaseId(String.valueOf(caseById.getCaseId()));
        model.setCategory(caseById.getCategory());
        model.setDamage_value(caseById.getDamageValue());
        model.setDetail(caseById.getDetailDetect());
        model.setLocation(location);
        model.setStatus(caseById.getStatus());
        model.setDate_opened(String.valueOf(caseById.getCases().getDate_opened()));
        model.setDate_closed(String.valueOf(caseById.getCases().getDate_closed()));
        model.setPicture(caseById.getCases().getPicture());
        model.setPicture_done(caseById.getCases().getPicture_done());
        model.setUser(userModel);
        return model;
    }

    public List<AllCasesModel> getAllCase() {
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCase();

        List<AllCasesModel> casesData = allCases.stream().map(entity -> {
            AllCasesModel model = new AllCasesModel();
            model.setCaseId(entity.getCases().getId());
            model.setStatus(entity.getStatus());
            model.setPicture(entity.getCases().getPicture());
            model.setDateOpened(entity.getCases().getDate_opened());
            model.setDamage_value(entity.getDamageValue());
            model.setCategory(entity.getCases().getCategory());
            model.setDetail(entity.getCases().getDetail());



            return model;
        }).collect(Collectors.toList());

        return casesData;
    }




}
