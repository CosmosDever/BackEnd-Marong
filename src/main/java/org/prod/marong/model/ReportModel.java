package org.prod.marong.model;

import lombok.Data;

@Data
public class ReportModel {
    private String id;
    private String status;
    private String damageValue;
    private String category;
    private String detailDetect;
    private String caseId;
    private String userId;
    private String createdAt;
    private String updatedAt;
}
