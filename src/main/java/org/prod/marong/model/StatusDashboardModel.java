package org.prod.marong.model;

import lombok.Data;

@Data
public class StatusDashboardModel {
    private String status;
    private Integer count;
    private Integer percent;

}
