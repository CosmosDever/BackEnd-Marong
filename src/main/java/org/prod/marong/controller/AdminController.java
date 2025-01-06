package org.prod.marong.controller;

import org.prod.marong.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
public class AdminController {
    private static final String SUCCESS = "200";
    private static final String ERROR = "400";

//    @Autowired
//    AdminService adminService;
//
}
