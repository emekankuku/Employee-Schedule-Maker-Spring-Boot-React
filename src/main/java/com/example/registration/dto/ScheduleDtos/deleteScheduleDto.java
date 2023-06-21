package com.example.registration.dto.ScheduleDtos;

public class deleteScheduleDto {

    private String email;

    private String group;

    public deleteScheduleDto(){}

    public deleteScheduleDto(String email, String group) {
        this.email = email;
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    
    
}
