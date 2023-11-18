package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.repositories.SecuredUserRepository;
import com.devdialogue.backend.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class SecuredUserService implements UserDetailsService {
    @Autowired
    private SecuredUserRepository securedUserRepository;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return securedUserRepository.findByUsername(username);
    }

    public SecuredUser save(SecuredUser securedUser, String userType) {
        if (securedUserRepository.findByUsername(securedUser.getUsername()) != null) return null;
        String encryptedPassword = passwordEncoder.encode(securedUser.getPassword());
        String authorities = Utils.getAuthoritiesForUsers().get(userType);
        System.out.println(authorities);
        securedUser.setPassword(encryptedPassword);
        securedUser.setAuthorities(authorities);
        return securedUserRepository.save(securedUser);
    }

}
