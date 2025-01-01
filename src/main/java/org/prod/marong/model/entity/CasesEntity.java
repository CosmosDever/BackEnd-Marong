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

    @Column(name = "location")
    private String location;

    @Column(name = "date_opened")
    private LocalDate date_opened;

    @Column(name = "date_closed")
    private LocalDate date_closed;

    @Column(name = "detail")
    private String detail;

}
