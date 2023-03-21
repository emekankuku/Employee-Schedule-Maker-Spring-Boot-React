package com.example.registration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.UserService;
import com.example.registration.dto.LoginDto;
import com.example.registration.model.User;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class ReactController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService service;

    @Autowired
    AuthenticationManager authenticationManager;

    public ReactController(UserService service) {
        this.service = service;
    }

    @GetMapping("/users")
    public List<User> fetchUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/currUser")
    public Authentication currUser(){
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth;
    }

    @GetMapping("/signin")
    public User authenticateUser() {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken("ravymiyazaki@gmail.com", "Asdfghjkl2;"));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return service.getCurrentUser(auth.getName());
        
    }
}
