package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class AllCasesModel {
    private Long caseId;
    private String category;
    private String detail;
    private String picture;
    private LocalDate dateOpened;
    private String status;
    private String damage_value;
}
