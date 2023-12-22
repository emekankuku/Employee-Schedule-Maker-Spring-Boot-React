package com.example.build.dto.ScheduleDtos;

import com.example.build.model.Schedule;

public class SchedOutput extends scheduleDto {

    private String name;

    public SchedOutput() {

    }

    public SchedOutput(String name){
        this.name = name;
    }

    public SchedOutput(String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday, String saturday) {
        super(sunday, monday, tuesday, wednesday, thursday, friday, saturday);
    }

    public SchedOutput(Schedule dto) {
        super(dto);
        this.name = dto.getFullName();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
