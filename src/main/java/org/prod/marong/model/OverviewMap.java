package org.prod.marong.model;

import lombok.Data;


@Data
public class OverviewMap {
    private String case_id;
    private String category;
    private LocationModel location;
    private String status;
    private String date_opened;
    private String date_closed;
    private String picture;
    private String picture_done;


}
