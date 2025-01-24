package org.prod.marong.model;

import lombok.Data;

@Data
public class ReportCaseResponseModel {
    private Long caseId;
    private String category;
    private String detail;
    private String picture;
    private LocationModel location;
    private String date_opened;
    private String status;

}
