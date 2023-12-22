package com.example.build.dto.GroupDtos;

public class CreateGroupDto extends GroupDto{

    private String email;

    public CreateGroupDto() {
        
    }

    public CreateGroupDto(String name, String email) {
        super(name);
        this.email = email;
    }

    public String getEmail(){
        return email;
    }

    
}
