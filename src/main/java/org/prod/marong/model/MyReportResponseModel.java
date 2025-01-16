package org.prod.marong.model;

import lombok.Data;

@Data
public class MyReportResponseModel {
    private String case_id;
    private String type_of_issue;
    private LocationModel location;
    private String date_report;
    private String status;

}
