package com.example.build.model;

import java.util.HashMap;
import java.util.Map;

import com.example.build.dto.Requests.SchedRequestDto;
import com.example.build.dto.ScheduleDtos.CreateScheduleDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "schedules")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String fullName;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Schedule() {
    }

    public Schedule(String email, String sunday, String monday, String tuesday, String wednesday, String thursday,
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

    public Schedule(CreateScheduleDto dto) {
        this.email = dto.getEmail();
        Sunday = dto.getSun();
        Monday = dto.getMon();
        Tuesday = dto.getTue();
        Wednesday = dto.getWed();
        Thursday = dto.getThur();
        Friday = dto.getFri();
        Saturday = dto.getSat();
    }

    public Schedule(SchedRequestDto dto) {
        this.email = dto.getEmail();
        Sunday = dto.getSun();
        Monday = dto.getMon();
        Tuesday = dto.getTue();
        Wednesday = dto.getWed();
        Thursday = dto.getThur();
        Friday = dto.getFri();
        Saturday = dto.getSat();
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

    public String getName() {
        return email;
    }

    public void setName(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Map<String, String> scheduleMap() {
        Map<String, String> map = new HashMap<>();
        map.put("sunday", Sunday);
        map.put("monday", Monday);
        map.put("tuesday", Tuesday);
        map.put("wednesday", Wednesday);
        map.put("thursday", Thursday);
        map.put("friday", Friday);
        map.put("saturday", Saturday);

        return map;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
