package com.example.build.dto.GroupDtos;

import com.example.build.model.Group;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class GroupDto {
    
    @NotNull(message = "Group name is required")
    @Size(min = 1, message = "Group name is required")
    private String name;

    public GroupDto(){
    }

    public GroupDto(Group group) {
        this.name = group.getName();
    }

    public GroupDto(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    
}
