package com.example.build.dto.DaysOffDtos;

import java.time.format.DateTimeFormatter;

import com.example.build.model.DaysOff;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class daysOffDto {

    private String email;

    private String group;

    @NotNull(message = "Start date is required")
    @Size(min = 1, message = "Start date is required")
    private String start;

    @NotNull(message = "End date is required")
    @Size(min = 1, message = "End date is required")
    private String end;

    @NotNull(message = "Note is required")
    @Size(min = 1, message = "Note is required")
    private String note;

    public daysOffDto() {
    }

    public daysOffDto(String start, String end, String note) {
        this.start = start;
        this.end = end;
        this.note = note;
    }
    

    public daysOffDto(DaysOff daysOff){
        this.start = daysOff.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.end = daysOff.getEnd().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.note = daysOff.getNote();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }
}
