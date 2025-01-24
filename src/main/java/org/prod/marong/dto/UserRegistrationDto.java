package org.prod.marong.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class UserRegistrationDto {
    private String fullName;
    private String gmail;
    private String password;
    private String telephone;
    private String gender;
    private LocalDate birthday;
}