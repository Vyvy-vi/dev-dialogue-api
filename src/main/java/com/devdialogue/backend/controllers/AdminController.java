package com.devdialogue.backend.controllers;

import com.devdialogue.backend.dtos.CreateAdminRequest;
import com.devdialogue.backend.dtos.CreateUserRequest;
import com.devdialogue.backend.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    AdminService adminService;
    @PostMapping
    public void createAdmin(@RequestBody @Valid CreateAdminRequest createAdminRequest) {
        adminService.createAdmin(createAdminRequest.to());
    }

    @GetMapping
    public String ping() {
        return "admin - pong";
    }
}
