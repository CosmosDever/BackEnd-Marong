package org.prod.marong.controller;

import org.prod.marong.dto.AdminDto;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/all")
    public ResponseEntity<Map<String, Object>> getAllAdmins() {
        List<AdminDto> admins = adminService.getAllAdmins();
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", admins);
        return ResponseEntity.ok(response);
    }
}
