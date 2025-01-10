package org.prod.marong.model;

import lombok.Data;

@Data
public class MyReportCaseModel {
    private String id;
    private String type_of_issue;
    private String location;
    private String picture;
    private String detail;
    private String damage_value;
    private String date_opened;
    private String date_closed;
    private String status;
    private String userId;
}
