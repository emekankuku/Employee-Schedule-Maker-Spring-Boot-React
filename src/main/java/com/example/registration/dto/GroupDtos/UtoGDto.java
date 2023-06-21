package com.example.registration.dto.GroupDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class UtoGDto {

    @NotNull(message = "Group name is required")
    @Size(min = 1, message = "Group name is required")
    private String groupName;

    private String userEmail;

    public UtoGDto() {
        
    }

    public UtoGDto(String groupName) {
        this.groupName = groupName;
    }

    public UtoGDto(String groupName, String userEmail) {
        this.groupName = groupName;
        this.userEmail = userEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public String getUserEmail(){
        return userEmail;
    }

    
}
