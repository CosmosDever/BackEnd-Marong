package org.prod.marong.service.cases;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.repository.CaseRepository;
import org.prod.marong.repository.NewsRepository;
import org.prod.marong.repository.ReportRepository;
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

    public List<CasesModel> getAllCaseWithStatus() {
        List<CasesEntity> allCases = caseRepository.findAllCase();

        List<CasesModel> casesData = allCases.stream().map(entity -> {
            CasesModel model = new CasesModel();
            // do you have another solve? 
            String statusById = reportRepository.findReportStatusById(String.valueOf(entity.getId()));
            model.setCaseId(entity.getId());
            model.setPicture(entity.getPicture());
            model.setDetail(entity.getDetail());
            model.setCategory(entity.getCategory());
            model.setLocation(entity.getLocation());
            model.setDateOpened(entity.getDate_opened());
            model.setReportStatus(statusById);
            return model;
        }).collect(Collectors.toList());

        return casesData;
    }









}
