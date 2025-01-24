package org.prod.marong.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;


@Entity
@Table(name = "reports")
@Data
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "damage_value")
    private String damageValue;

    @Column(name = "category")
    private String category;

    @Column(name = "detail_detect")
    private String detailDetect;

    @Column(name = "case_id")
    private Integer caseId;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}