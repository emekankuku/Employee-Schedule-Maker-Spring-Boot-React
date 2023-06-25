package com.example.build.dto.GroupDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateGroupDto {

    @NotNull(message = "Group name is required")
    @Size(min = 1, message = "Group name is required")
    private String name;

    private String email;

    public CreateGroupDto() {
        
    }

    public CreateGroupDto(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail(){
        return email;
    }

    
}
