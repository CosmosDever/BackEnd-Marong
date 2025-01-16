package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CasesByIdModel {
    private String caseId;
    private String category;
    private String detail;
    private LocationModel location;
    private String damage_value;
    private String status;
    private String date_opened;
    private String date_closed;
    private String picture;
    private String picture_done;
    private UserCaseModel user;



}
