package org.prod.marong.model;

import lombok.Data;

@Data
public class CasesDashboardModel {
    private String type_of_issues;
    private Integer count;
    private Integer percent;

}
