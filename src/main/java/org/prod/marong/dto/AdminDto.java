package org.prod.marong.dto;

import lombok.Data;

@Data
public class AdminDto {
    private Long id;
    private String picture;
    private String fullName;
    private String role;

    // Getters and setters
}