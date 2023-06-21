package com.example.registration.dto.DaysOffDtos;

import com.example.registration.model.DaysOff;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class daysOffDto {

    private String email;

    private String group;

    @NotNull(message = "Year is required")
    @Size(min = 1, message = "Year is required")
    private String startYear;

    @NotNull(message = "Month is required")
    @Size(min = 1, message = "Month is required")
    private String startMonth;

    @NotNull(message = "Day is required")
    @Size(min = 1, message = "Day is required")
    private String startDay;

    @NotNull(message = "Year is required")
    @Size(min = 1, message = "Year is required")
    private String endYear;

    @NotNull(message = "Month is required")
    @Size(min = 1, message = "Month is required")
    private String endMonth;

    @NotNull(message = "Day is required")
    @Size(min = 1, message = "Day is required")
    private String endDay;

    @NotNull(message = "Note is required")
    @Size(min = 1, message = "Note is required")
    private String note;

    public daysOffDto() {
    }

    public daysOffDto(String startYear, String startMonth, String startDay, String endYear, String endMonth,
            String endDay, String note) {
        this.startYear = startYear;
        this.startMonth = startMonth;
        this.startDay = startDay;
        this.endYear = endYear;
        this.endMonth = endMonth;
        this.endDay = endDay;
        this.note = note;
    }
    

    public daysOffDto(DaysOff daysOff){
        this.startYear = Integer.toString(daysOff.getStartDate().getYear());
        this.startMonth = Integer.toString(daysOff.getStartDate().getMonthValue());
        this.startDay = Integer.toString(daysOff.getStartDate().getDayOfMonth());
        this.endYear = Integer.toString(daysOff.getEndDate().getYear());
        this.endMonth = Integer.toString(daysOff.getEndDate().getMonthValue());
        this.endDay = Integer.toString(daysOff.getEndDate().getDayOfMonth());
        this.note = daysOff.getNote();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getStartYear() {
        return startYear;
    }

    public void setStartYear(String startYear) {
        this.startYear = startYear;
    }

    public String getStartMonth() {
        return startMonth;
    }

    public void setStartMonth(String startMonth) {
        this.startMonth = startMonth;
    }

    public String getStartDay() {
        return startDay;
    }

    public void setStartDay(String startDay) {
        this.startDay = startDay;
    }

    public String getEndYear() {
        return endYear;
    }

    public void setEndYear(String endYear) {
        this.endYear = endYear;
    }

    public String getEndMonth() {
        return endMonth;
    }

    public void setEndMonth(String endMonth) {
        this.endMonth = endMonth;
    }

    public String getEndDay() {
        return endDay;
    }

    public void setEndDay(String endDay) {
        this.endDay = endDay;
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
