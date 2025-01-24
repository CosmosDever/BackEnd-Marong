package org.prod.marong.service.overview;

import org.prod.marong.model.*;
import org.prod.marong.model.entity.ReportJoinCaseEntity;
import org.prod.marong.repository.ReportJoinCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
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
            LocationModel location = new LocationModel();
            location.setDescription(reportCase.getCases().getLocation_description());
            String[] coordinates = new String[2];
            coordinates[0] = reportCase.getCases().getLatitude();
            coordinates[1] = reportCase.getCases().getLongitude();
            location.setCoordinates(coordinates);
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(location);
            overviewMap.setStatus(reportCase.getStatus());
            overviewMap.setPicture(reportCase.getCases().getPicture());
            overviewMap.setPicture_done(reportCase.getCases().getPicture_done());
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_opened()));
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_closed()));
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
                case "Cancel":
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
            LocationModel location = new LocationModel();
            location.setDescription(reportCase.getCases().getLocation_description());
            String[] coordinates = new String[2];
            coordinates[0] = reportCase.getCases().getLatitude();
            coordinates[1] = reportCase.getCases().getLongitude();
            location.setCoordinates(coordinates);
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(location);
            overviewMap.setStatus(reportCase.getStatus());
            overviewMap.setPicture(reportCase.getCases().getPicture());
            overviewMap.setPicture_done(reportCase.getCases().getPicture_done());
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_opened()));
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_closed()));
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
                case "Cancel":
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
            LocationModel location = new LocationModel();
            location.setDescription(reportCase.getCases().getLocation_description());
            String[] coordinates = new String[2];
            coordinates[0] = reportCase.getCases().getLatitude();
            coordinates[1] = reportCase.getCases().getLongitude();
            location.setCoordinates(coordinates);
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(location);
            overviewMap.setStatus(reportCase.getStatus());
            overviewMap.setPicture(reportCase.getCases().getPicture());
            overviewMap.setPicture_done(reportCase.getCases().getPicture_done());
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_opened()));
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_closed()));
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
                case "Cancel":
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
            LocationModel location = new LocationModel();
            location.setDescription(reportCase.getCases().getLocation_description());
            String[] coordinates = new String[2];
            coordinates[0] = reportCase.getCases().getLatitude();
            coordinates[1] = reportCase.getCases().getLongitude();
            location.setCoordinates(coordinates);
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(location);
            overviewMap.setStatus(reportCase.getStatus());
            overviewMap.setPicture(reportCase.getCases().getPicture());
            overviewMap.setPicture_done(reportCase.getCases().getPicture_done());
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_opened()));
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_closed()));
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
                case "Cancel":
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
            LocationModel location = new LocationModel();
            location.setDescription(reportCase.getCases().getLocation_description());
            String[] coordinates = new String[2];
            coordinates[0] = reportCase.getCases().getLatitude();
            coordinates[1] = reportCase.getCases().getLongitude();
            location.setCoordinates(coordinates);
            overviewMap.setCase_id(reportCase.getCases().getId().toString());
            overviewMap.setCategory(reportCase.getCategory());
            overviewMap.setLocation(location);
            overviewMap.setStatus(reportCase.getStatus());
            overviewMap.setPicture(reportCase.getCases().getPicture());
            overviewMap.setPicture_done(reportCase.getCases().getPicture_done());
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_opened()));
            overviewMap.setDate_opened(String.valueOf(reportCase.getCases().getDate_closed()));
            toMap.add(overviewMap);
        }
        dataOverview.setToMap(toMap);
        return dataOverview;
    }

    public DashboardStatusModel getDashboardAllStatusCase(String id){
        List<ReportJoinCaseEntity> allFromDb = reportJoinCaseRepository.findReportJoinCaseByUserId(id);
        int inProgress = 0;
        int waiting = 0;
        int done = 0;
        int cancel = 0;

        for (ReportJoinCaseEntity reportCase : allFromDb) {
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
                case "Cancel":
                    cancel++;
                    break;
                default:
                    break;
            }
        }
        Integer AllSumStatus =  inProgress+waiting+done+cancel;
        DashboardStatusModel dashboardData = new DashboardStatusModel();
        dashboardData.setTotal_cases(String.valueOf(AllSumStatus));

        List<String> casesString = new ArrayList<>();
        casesString.add("waiting");
        casesString.add("in progress");
        casesString.add("done");
        casesString.add("cancel");
        List<StatusDashboardModel> AllCases = new ArrayList<>();
        for (String string : casesString){
            StatusDashboardModel cases = new StatusDashboardModel();
            cases.setStatus(string);
            if (string.equals("Waiting")){
                cases.setCount(waiting);
                cases.setPercent((waiting/AllSumStatus)*100);
            }
            if (string.equals("InProgress")){
                cases.setCount(inProgress);
                cases.setPercent((inProgress/AllSumStatus)*100);
            }
            if (string.equals("Done")){
                cases.setCount(done);
                cases.setPercent((done/AllSumStatus)*100);
            }
            if (string.equals("Cancel")){
                cases.setCount(cancel);
                cases.setPercent((cancel/AllSumStatus)*100);
            }
            AllCases.add(cases);
        }
        dashboardData.setCases(AllCases);



        return dashboardData;
    }

    public DashboardModel getDashboardAllCase(String id){
        List<ReportJoinCaseEntity> allFromDb = reportJoinCaseRepository.findReportJoinCaseByUserId(id);
        int road = 0;
        int pavement = 0;
        int overpass = 0;
        int wire = 0;

        for (ReportJoinCaseEntity reportCase : allFromDb) {
            String status = reportCase.getStatus();

            switch (status) {
                case "road":
                    road++;
                    break;
                case "pavement":
                    pavement++;
                    break;
                case "overpass":
                    overpass++;
                    break;
                case "wire":
                    wire++;
                    break;
                default:
                    break;
            }
        }
        Integer AllSumStatus =  road+pavement+overpass+wire;
        DashboardModel dashboardData = new DashboardModel();
        dashboardData.setTotal_cases(String.valueOf(AllSumStatus));

        List<String> casesString = new ArrayList<>();
        casesString.add("pavement");
        casesString.add("road");
        casesString.add("done");
        casesString.add("cancel");
        List<CasesDashboardModel> AllCases = new ArrayList<>();
        for (String string : casesString){
            CasesDashboardModel cases = new CasesDashboardModel();
            cases.setType_of_issues(string);
            if (string.equals("pavement")){
                cases.setCount(pavement);
                cases.setPercent((pavement/AllSumStatus)*100);
            }
            if (string.equals("road")){
                cases.setCount(road);
                cases.setPercent((road/AllSumStatus)*100);
            }
            if (string.equals("overpass")){
                cases.setCount(overpass);
                cases.setPercent((overpass/AllSumStatus)*100);
            }
            if (string.equals("wire")){
                cases.setCount(wire);
                cases.setPercent((wire/AllSumStatus)*100);
            }
            AllCases.add(cases);
        }
        dashboardData.setCases(AllCases);



        return dashboardData;
    }

}
