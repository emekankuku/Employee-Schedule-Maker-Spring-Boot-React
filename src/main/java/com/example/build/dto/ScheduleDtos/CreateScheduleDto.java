package com.example.build.dto.ScheduleDtos;

import com.example.build.model.Schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class CreateScheduleDto extends scheduleDto {

    private String groupName;

    @NotNull(message = "Email is required")
    @Size(min = 1, message = "Email is required")
    private String email;

    public CreateScheduleDto() {
    }

    public CreateScheduleDto(String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday, String saturday) {
        super(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    public CreateScheduleDto(Schedule dto) {
        super(dto);
    }

    public String getGroup() {
        return groupName;
    }

    public void setGroup(String group) {
        this.groupName = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
