package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.Admin;
import com.devdialogue.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepository;

    public void createAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public Admin getAdmin(int id) {
        return adminRepository.findById(id).orElse(null);
    }
}