package org.prod.marong.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
@Entity
@Table(name = "roles")
@Data

public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "role_name", nullable = false, unique = true)
    private String name;

}