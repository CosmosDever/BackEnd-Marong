package org.prod.marong.service.cases;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.CasesEntity;
import org.prod.marong.model.entity.NewsEntity;
import org.prod.marong.model.entity.ReportEntity;
import org.prod.marong.repository.CaseRepository;
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

//    public List<CasesModel> getAllCase() {
//        List<CasesModel> allCase = caseRepository.findCasesWithReportStatus();
//
//        List<CasesModel> caseData = allCase.stream().map(entity -> {
//            CasesModel model = new CasesModel();
//            model.setId(entity.getId().toString());
//            model.setPicture(entity.getPicture());
//            model.setCategory(entity.getCategory());
//            model.setDate_opened(String.valueOf(entity.getDate_opened()));
//            model.setDetail(entity.getDetail());
//            model.setStatus(entity.getStatus());
//            return model;
//        }).collect(Collectors.toList());
//
//        return caseData;
//    }





}
