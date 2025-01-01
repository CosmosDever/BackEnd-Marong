package org.prod.marong.service;

import org.prod.marong.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {
    @Autowired
    ReportRepository reportRepository;
   public String getTestData() {
       List<String> testData = reportRepository.findReportStatusById("1");
       return "Sample Test Data";
   }


}
