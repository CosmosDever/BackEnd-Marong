package org.prod.marong.model;

import lombok.Data;

@Data
public class ChangeStatusDoneResponseModel {
    private String case_id;
    private String status;
    private String detail;
    private String date_updated;
    private String picture_done;
}
