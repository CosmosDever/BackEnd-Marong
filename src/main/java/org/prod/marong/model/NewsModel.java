package org.prod.marong.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class NewsModel {
    private String id;
    private String picture;
    private String title;
    private LocalDate date;
    private LocationModel location;
    private String content;
    private String type;
}
