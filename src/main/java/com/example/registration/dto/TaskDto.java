package com.example.registration.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class TaskDto {

    @NotNull(message = "Name is required")
    private String name;

    private String date;

    private String time;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
