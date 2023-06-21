package com.example.registration.dto.AuthenticationDtos;

import java.util.List;


public class JwtResponse {

    public String token;
    public long id;
    public String firstName;
    public String lastName;
    public String email;
    public String role;

    public JwtResponse(String accessToken, Long id, String firstName, String lastName, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(Long id, String firstName, String lastName, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.role = role;
    }

    public JwtResponse(Long id, String firstName, String lastName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
