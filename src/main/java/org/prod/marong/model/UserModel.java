package org.prod.marong.model;

import lombok.Data;

@Data
public class UserModel {
    private Long id;
    private String gmail;
    private String password;
    private String fullName;
    private String birthday; // Using String for simplicity in transfer
    private String telephone;
    private String gender;
    private String picture;
    private String roles;

}
