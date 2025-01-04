package org.prod.marong.model.entity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "gmail", nullable = false, unique = true)
    private String gmail;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "telephone", nullable = false)
    private String telephone;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "picture")
    private String picture;

    @Column(name = "birthday", nullable = false)
    private LocalDate birthday;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id" ,referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id")
    )
    private List<RoleEntity> roles = new ArrayList<>();
}