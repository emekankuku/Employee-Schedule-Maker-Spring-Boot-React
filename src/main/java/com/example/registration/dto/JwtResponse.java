package com.example.registration.dto;

import java.util.List;

import com.example.registration.model.Role;

public class JwtResponse {

    public String token;
    public long id;
    public String firstName;
    public String lastName;
    public String email;
    public List<String> roles;

    public JwtResponse(String accessToken, Long id, String firstName, String lastName, String email, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(Long id, String firstName, String lastName, String email, List<String> roles) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.roles = roles;
    }

    public JwtResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
