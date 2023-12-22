package com.example.build.dto.CheckIn;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CheckInDto {

    @NotNull(message = "Group is required")
    @Size(min = 1, message = "Group is required")
    private String group;
    
    @NotNull(message = "Email is required")
    @Size(min = 1, message = "Email is required")
    private String userEmail;

    public CheckInDto(){

    }

    public CheckInDto(String group, String email) {
        this.group = group;
        this.userEmail = email;
    }

    public CheckInDto(String email) {
        this.userEmail = email;
    }

    public String getEmail() {
        return userEmail;
    }

    public void setEmail(String email) {
        this.userEmail = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    
}
