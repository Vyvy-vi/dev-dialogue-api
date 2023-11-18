package com.devdialogue.backend.services;

import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecuredUserService securedUserService;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public void createUser(User user) {
        SecuredUser securedUser = user.getSecuredUser();
        securedUser = securedUserService.save(securedUser, "USER");
        if (securedUser == null) return;
        user.setSecuredUser(securedUser);
        userRepository.save(user);
    }

    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
}
