package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.Admin;
import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.repositories.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private SecuredUserService securedUserService;
    @Autowired
    private AdminRepository adminRepository;

    public void createAdmin(Admin admin) {
        SecuredUser securedUser = admin.getSecuredUser();
        securedUser = securedUserService.save(securedUser, "ADMIN");

        if (securedUser == null) return;

        admin.setSecuredUser(securedUser);
        adminRepository.save(admin);
    }

    public Admin getAdmin(int id) {
        return adminRepository.findById(id).orElse(null);
    }
}