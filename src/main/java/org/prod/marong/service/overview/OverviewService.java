package org.prod.marong.service.overview;

import org.prod.marong.model.OverviewMap;
import org.prod.marong.model.OverviewModel;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.repository.ReportJoinCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class OverviewService {

    @Autowired
    ReportJoinCaseRepository reportJoinCaseRepository;

    public List<ReportJoinCaseEntity> getAllOverviewTest(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCase();
        return allCases;
    }

    public OverviewModel getAllOverview(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCase();
        OverviewModel dataOverview = new OverviewModel();
        // Initialize counters for each status
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allCases) {
            String status = reportCase.getStatus();

            switch (status) {
                case "InProgress":
                    inProgress++;
                    break;
                case "Waiting":
                    waiting++;
                    break;
                case "Done":
                    done++;
                    break;
                case "closed":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        dataOverview.setInprogress_all_cases(inProgress);
        dataOverview.setWaiting_all_cases(waiting);
        dataOverview.setDone_all_case(done);
        dataOverview.setTotal_all_cases(allCases.size());
        dataOverview.setCancel_all_cases(cancel);
        List<OverviewMap> toMap = new ArrayList<>();
        for (ReportJoinCaseEntity reportCase : allCases) {
            OverviewMap overviewMap = new OverviewMap();
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(reportCase.getCases().getLocation());
            overviewMap.setStatus(reportCase.getStatus());
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }

    public OverviewModel getAllOverviewRoad(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCaseCategory("Road Damage");
        OverviewModel dataOverview = new OverviewModel();
        // Initialize counters for each status
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allCases) {
            String status = reportCase.getStatus();

            switch (status) {
                case "InProgress":
                    inProgress++;
                    break;
                case "Waiting":
                    waiting++;
                    break;
                case "Done":
                    done++;
                    break;
                case "closed":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        dataOverview.setInprogress_all_cases(inProgress);
        dataOverview.setWaiting_all_cases(waiting);
        dataOverview.setDone_all_case(done);
        dataOverview.setTotal_all_cases(allCases.size());
        dataOverview.setCancel_all_cases(cancel);
        List<OverviewMap> toMap = new ArrayList<>();
        for (ReportJoinCaseEntity reportCase : allCases) {
            OverviewMap overviewMap = new OverviewMap();
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(reportCase.getCases().getLocation());
            overviewMap.setStatus(reportCase.getStatus());
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }

    public OverviewModel getAllOverviewPavement(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCaseCategory("Damaged Sidewalk");
        OverviewModel dataOverview = new OverviewModel();
        // Initialize counters for each status
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allCases) {
            String status = reportCase.getStatus();

            switch (status) {
                case "InProgress":
                    inProgress++;
                    break;
                case "Waiting":
                    waiting++;
                    break;
                case "Done":
                    done++;
                    break;
                case "closed":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        dataOverview.setInprogress_all_cases(inProgress);
        dataOverview.setWaiting_all_cases(waiting);
        dataOverview.setDone_all_case(done);
        dataOverview.setTotal_all_cases(allCases.size());
        dataOverview.setCancel_all_cases(cancel);
        List<OverviewMap> toMap = new ArrayList<>();
        for (ReportJoinCaseEntity reportCase : allCases) {
            OverviewMap overviewMap = new OverviewMap();
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(reportCase.getCases().getLocation());
            overviewMap.setStatus(reportCase.getStatus());
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }

    public OverviewModel getAllOverviewOverpass(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCaseCategory("Overpass Damage");
        OverviewModel dataOverview = new OverviewModel();
        // Initialize counters for each status
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allCases) {
            String status = reportCase.getStatus();

            switch (status) {
                case "InProgress":
                    inProgress++;
                    break;
                case "Waiting":
                    waiting++;
                    break;
                case "Done":
                    done++;
                    break;
                case "closed":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        dataOverview.setInprogress_all_cases(inProgress);
        dataOverview.setWaiting_all_cases(waiting);
        dataOverview.setDone_all_case(done);
        dataOverview.setTotal_all_cases(allCases.size());
        dataOverview.setCancel_all_cases(cancel);
        List<OverviewMap> toMap = new ArrayList<>();
        for (ReportJoinCaseEntity reportCase : allCases) {
            OverviewMap overviewMap = new OverviewMap();
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(reportCase.getCases().getLocation());
            overviewMap.setStatus(reportCase.getStatus());
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }

    public OverviewModel getAllOverviewWire(){
        List<ReportJoinCaseEntity> allCases = reportJoinCaseRepository.findAllReportJoinCaseCategory("Wire Damage");
        OverviewModel dataOverview = new OverviewModel();
        // Initialize counters for each status
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allCases) {
            String status = reportCase.getStatus();

            switch (status) {
                case "InProgress":
                    inProgress++;
                    break;
                case "Waiting":
                    waiting++;
                    break;
                case "Done":
                    done++;
                    break;
                case "closed":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        dataOverview.setInprogress_all_cases(inProgress);
        dataOverview.setWaiting_all_cases(waiting);
        dataOverview.setDone_all_case(done);
        dataOverview.setTotal_all_cases(allCases.size());
        dataOverview.setCancel_all_cases(cancel);
        List<OverviewMap> toMap = new ArrayList<>();
        for (ReportJoinCaseEntity reportCase : allCases) {
            OverviewMap overviewMap = new OverviewMap();
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(reportCase.getCases().getLocation());
            overviewMap.setStatus(reportCase.getStatus());
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }



}
