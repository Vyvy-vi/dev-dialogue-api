package com.devdialogue.backend.controllers;

import com.devdialogue.backend.domain.SecuredUser;
import com.devdialogue.backend.domain.User;
import com.devdialogue.backend.dtos.CreateUserRequest;
import com.devdialogue.backend.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();
        User user = securedUser.getUser();
        if (user == null) return userService.findAll();
        return Arrays.asList(userService.getUser(user.getId()));
    }

    @PostMapping
    public void createUser(@RequestBody @Valid CreateUserRequest createUserRequest) {
        userService.createUser(createUserRequest.to());
    }

    // admin-only
    @GetMapping("/{id}")
    public User getUserById(@PathVariable("id") int id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecuredUser securedUser = (SecuredUser) authentication.getPrincipal();

        boolean isCalledByAdmin = securedUser.getAuthorities().stream().anyMatch(x->x.getAuthority() == "ADMIN");
        if (isCalledByAdmin) return userService.getUser(id);
        throw new RuntimeException("User is not authorised");
    }
}
