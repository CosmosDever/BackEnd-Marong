package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class DashboardModel {
    private String total_cases;
    private List<CasesDashboardModel> cases;
}
