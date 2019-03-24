package com.example.salesoldvehicle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.salesoldvehicle.exception.ResourceNotFoundException;
import com.example.salesoldvehicle.model.User;
import com.example.salesoldvehicle.repository.UserRepository;
import com.example.salesoldvehicle.security.CurrentUser;
import com.example.salesoldvehicle.security.UserPrincipal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
    
    @GetMapping("/test")
    public String test() {
        return "AAAAAAAAAAA";
    }
}
