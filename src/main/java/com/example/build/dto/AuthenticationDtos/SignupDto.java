package com.example.build.dto.AuthenticationDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class SignupDto extends LoginDto{

    @NotNull(message = "First Name is required")
    @Size(min = 1, message = "First Name is required")
    private String firstName;

    @NotNull(message = "Last Name is required")
    @Size(min = 1, message = "Last Name is required")
    private String lastName;

    @Size(min = 1, message = "Role is required")
    private String role;

    public SignupDto() {

    }

    public SignupDto(String firstName, String lastName, String email, String password, String role) {
        super(email, password);
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

}