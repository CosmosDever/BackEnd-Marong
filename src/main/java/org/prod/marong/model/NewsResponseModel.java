package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsResponseModel {
    private String id;
    private String picture;
    private String title;
    private LocalDate date;
    private String location;
    private String content;
    private String type;
    private String last_updated;
}
