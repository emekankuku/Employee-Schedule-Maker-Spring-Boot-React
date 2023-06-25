package com.example.build.dto.GroupDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class addScheduleDto {

    @NotNull(message = "Email is required")
    @Size(min = 1, message = "Email is required")
    private String email;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Sunday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Monday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Tuesday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Wednesday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Thursday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Friday;

    @NotNull(message = "Time is required")
    @Size(min = 1, message = "Time is required")
    private String Saturday;

    public addScheduleDto(){
    }

    public addScheduleDto(String email, String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday, String saturday) {
        this.email = email;
        Sunday = sunday;
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSunday() {
        return Sunday;
    }

    public void setSunday(String sunday) {
        Sunday = sunday;
    }

    public String getMonday() {
        return Monday;
    }

    public void setMonday(String monday) {
        Monday = monday;
    }

    public String getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String tuesday) {
        Tuesday = tuesday;
    }

    public String getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String wednesday) {
        Wednesday = wednesday;
    }

    public String getThursday() {
        return Thursday;
    }

    public void setThursday(String thursday) {
        Thursday = thursday;
    }

    public String getFriday() {
        return Friday;
    }

    public void setFriday(String friday) {
        Friday = friday;
    }

    public String getSaturday() {
        return Saturday;
    }

    public void setSaturday(String saturday) {
        Saturday = saturday;
    }

    

}
