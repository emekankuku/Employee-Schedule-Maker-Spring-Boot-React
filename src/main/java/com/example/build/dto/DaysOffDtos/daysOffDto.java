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
    private String startDate;

    @NotNull(message = "End date is required")
    @Size(min = 1, message = "End date is required")
    private String endDate;

    @NotNull(message = "Note is required")
    @Size(min = 1, message = "Note is required")
    private String note;

    public daysOffDto() {
    }

    public daysOffDto(String startDate, String endDate, String note) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }
    

    public daysOffDto(DaysOff daysOff){
        this.startDate = daysOff.getStartDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.endDate = daysOff.getEndDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.note = daysOff.getNote();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
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
