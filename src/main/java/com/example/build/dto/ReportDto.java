package com.example.build.dto;

import java.util.List;
import java.util.Map;

import com.example.build.model.Report;

public class ReportDto {
    
    private List<String> emails;

    private Map<String, Long> checkinHours;

    private Map<String, Double> actualHours;

    public ReportDto(){

    }

    public ReportDto(Report report){
        this.emails = report.getEmails();
        this.checkinHours = report.getCheckinHours();
        this.actualHours = report.getActualHours();
    }

    public List<String> getEmails() {
        return emails;
    }

    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    public Map<String, Long> getCheckinHours() {
        return checkinHours;
    }

    public void setCheckinHours(Map<String, Long> checkinHours) {
        this.checkinHours = checkinHours;
    }

    public Map<String, Double> getActualHours() {
        return actualHours;
    }

    public void setActualHours(Map<String, Double> actualHours) {
        this.actualHours = actualHours;
    }

    

}
