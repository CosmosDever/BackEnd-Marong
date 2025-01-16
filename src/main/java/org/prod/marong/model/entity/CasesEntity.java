package org.prod.marong.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "cases")
@Data
public class CasesEntity {
    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "picture")
    private String picture;

    @Column(name = "category")
    private String category;

    @Column(name = "location_description")
    private String location_description;

    @Column(name = "latitude")
    private String latitude;

    @Column(name = "longitude")
    private String longitude;

    @Column(name = "date_opened")
    private LocalDate date_opened;

    @Column(name = "date_closed")
    private LocalDate date_closed;

    @Column(name = "detail")
    private String detail;

    @Column(name = "picture_done")
    private String picture_done;

}
