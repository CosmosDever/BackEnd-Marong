package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ReportCaseModel {
    private String category;
    private String detail;
    private String picture;
    private LocationModel location;

}
