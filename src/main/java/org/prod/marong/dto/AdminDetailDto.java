package org.prod.marong.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class AdminDetailDto {
    private Long id;
    private String picture;
    private String fullName;
    private String gmail;
    private String password;
    private LocalDate birthday;
    private String gender;
    private String telephone;
    private String role;
}