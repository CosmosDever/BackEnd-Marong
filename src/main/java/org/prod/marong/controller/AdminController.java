package org.prod.marong.controller;

import org.prod.marong.dto.AdminDetailDto;
import org.prod.marong.dto.AdminDto;
import org.prod.marong.model.ResponseModel;
import org.prod.marong.service.admin.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
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

       @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getAdminById(@PathVariable Long id) {
        AdminDetailDto admin = adminService.getAdminById(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", admin);
        return ResponseEntity.ok(response);
    }

      @PatchMapping("/{id}/edit")
    @PreAuthorize("hasRole('ROLE_Admin') or hasRole('ROLE_master Admin')")
    public ResponseEntity<Map<String, Object>> updateAdmin(@PathVariable Long id, @RequestBody AdminDetailDto adminDetailDto) {
        AdminDetailDto updatedAdmin = adminService.updateAdmin(id, adminDetailDto);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Administrator details updated successfully.");
        response.put("data", updatedAdmin);
        return ResponseEntity.ok(response);
    }

      @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_master Admin')")
    public ResponseEntity<Map<String, Object>> createAdmin(@RequestBody AdminDetailDto adminDetailDto) {
        AdminDetailDto newAdmin = adminService.createAdmin(adminDetailDto);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Administrator account created successfully.");
        response.put("data", newAdmin);
        return ResponseEntity.ok(response);
    }

       @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasRole('ROLE_master Admin')")
    public ResponseEntity<Map<String, Object>> deleteAdmin(@PathVariable Long id) {
        adminService.deleteAdmin(id);
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("message", "Administrator account " + id + " deleted successfully.");
        return ResponseEntity.ok(response);
    }



}
