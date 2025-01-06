package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CasesModel {
    private Long caseId;
    private String category;
    private String detail;
    private String picture;
    private String location;
    private LocalDate dateOpened;
    private LocalDate dateClosed;
    private String reportStatus;
}
