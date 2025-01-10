package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class OverviewModel {
    private int total_all_cases;
    private int waiting_all_cases;
    private int inprogress_all_cases;
    private int done_all_case;
    private int cancel_all_cases;
    private List<OverviewMap> toMap;



}
