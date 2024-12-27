package org.prod.marong.model.entity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "gmail")
    private String gmail;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "birthday")
    private LocalDate birthday;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "gender")
    private String gender;

    @Column(name = "picture")
    private String picture;

    @Column(name = "roles")
    private String roles;
}