package com.example.registration.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupDto {

    @NotNull(message = "First Name is required")
    @Size(min = 1, message = "First Name is required")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(min = 1, message = "Last Name is required")
    private String lastName;

    @NotNull(message = "Email is required")
    @Size(min = 1, message = "Email is required")
    @Email(message = "Email must be well-formatted")
    private String email;

    @NotNull(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    public SignupDto(){
        
    }

    public SignupDto(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    
}