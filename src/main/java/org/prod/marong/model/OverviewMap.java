package org.prod.marong.model;

import lombok.Data;


@Data
public class OverviewMap {
    private String case_id;
    private String category;
    private LocationModel location;
    private String status;

}
