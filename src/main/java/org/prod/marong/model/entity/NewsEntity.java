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
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ใช้ AUTO_INCREMENT
    private Long id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "title")
    private String title;

    @Column(name = "published_at", columnDefinition = "DATETIME DEFAULT NOW()")
    private LocalDate date;

    @Column(name = "location_description")
    private String location_description;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "content")
    private String content;

    @Column(name = "type")
    private String type;

   
}