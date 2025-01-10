package org.prod.marong.model;

import lombok.Data;

import java.util.List;

@Data
public class DashboardStatusModel {
    private String total_cases;
    private List<StatusDashboardModel> cases;
}
