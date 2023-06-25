package com.example.build.dto.GroupDtos;

import com.example.build.model.Group;

public class GroupDto {
    
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
