package org.prod.marong.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "news")
@Data
public class NewsEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "title")
    private String detail;

    @Column(name = "published_at")
    private LocalDate date;

    @Column(name = "location")
    private String location;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;
}