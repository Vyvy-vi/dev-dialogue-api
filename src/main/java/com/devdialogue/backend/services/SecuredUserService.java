package com.devdialogue.backend.services;

import com.devdialogue.backend.repositories.SecuredUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SecuredUserService implements UserDetailsService {
    @Autowired
    private SecuredUserRepository securedUserRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return securedUserRepository.findByEmail(email);
    }

}