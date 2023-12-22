package com.example.build.model.Requests;

import com.example.build.dto.ScheduleDtos.CreateScheduleDto;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class SchedRequest extends Request {

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false)
    private String groupName;

    @Column(nullable = false)
    private String Sunday;

    @Column(nullable = false)
    private String Monday;

    @Column(nullable = false)
    private String Tuesday;

    @Column(nullable = false)
    private String Wednesday;

    @Column(nullable = false)
    private String Thursday;

    @Column(nullable = false)
    private String Friday;

    @Column(nullable = false)
    private String Saturday;

    public SchedRequest() {

    }

    public SchedRequest(String email, String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday,
            String saturday) {
        this.email = email;
        Sunday = sunday;
        Monday = monday;
        Tuesday = tuesday;
        Wednesday = wednesday;
        Thursday = thursday;
        Friday = friday;
        Saturday = saturday;
    }

    public SchedRequest(CreateScheduleDto dto) {
        this.email = dto.getEmail();
        Sunday = dto.getSun();
        Monday = dto.getMon();
        Tuesday = dto.getTue();
        Wednesday = dto.getWed();
        Thursday = dto.getThur();
        Friday = dto.getFri();
        Saturday = dto.getSat();
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

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

}
