package com.example.build.dto.DaysOffDtos;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.build.model.DaysOff;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class DaysOffOutputDto {

    private String name;

    private String start;

    private String end;

    @NotNull(message = "Note is required")
    @Size(min = 1, message = "Note is required")
    private String note;

    public DaysOffOutputDto() {
    }

    public DaysOffOutputDto(String name, LocalDate start, LocalDate end, String note) {
        this.name = name;
        this.start = start.getMonthValue() + '-' + start.getDayOfMonth() + "-" + start.getYear();
        this.end = end.getMonthValue() + '-' + end.getDayOfMonth() + "-" + end.getYear();;
        this.note = note;
    }
    

    public DaysOffOutputDto(DaysOff daysOff){
        this.name = daysOff.getFullName();
        this.start = daysOff.getStart().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.end = daysOff.getEnd().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.note = daysOff.getNote();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart() {
        return start;
    }

    public void setStart(LocalDate start) {
        this.start = start.getMonthValue() + '-' + start.getDayOfMonth() + "-" + start.getYear();
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end.getMonthValue() + '-' + end.getDayOfMonth() + "-" + end.getYear();
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
