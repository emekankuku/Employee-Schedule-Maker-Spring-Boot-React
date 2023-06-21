package com.example.registration.dto.ScheduleDtos;

import com.example.registration.model.Schedule;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class CreateScheduleDto {
    
    private String email;

    private String group;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String sunday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String monday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String tuesday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String wednesday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String thursday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String friday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    @Pattern(regexp = "^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Incorrect Time Range Format")
    private String saturday;

    public CreateScheduleDto() {
    }

    public CreateScheduleDto(String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday, String saturday) {
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public CreateScheduleDto(Schedule dto) {
        this.sunday = dto.getSunday();
        this.monday = dto.getMonday();
        this.tuesday = dto.getTuesday();
        this.wednesday = dto.getWednesday();
        this.thursday = dto.getThursday();
        this.friday = dto.getFriday();
        this.saturday = dto.getSaturday();
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
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
