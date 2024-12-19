package com.example.demo.domain.user.controller;

import com.example.demo.domain.user.service.AdminService;
import com.example.demo.domain.user.dto.ReportRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admins")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PostMapping("/report-users")
    public void reportUsers(@RequestBody ReportRequestDto reportRequestDto) {
        adminService.reportUsers(reportRequestDto.getUserIds());
    }
}
