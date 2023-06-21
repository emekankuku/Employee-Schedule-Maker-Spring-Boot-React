package com.example.registration.model;

import com.example.registration.dto.GroupDtos.addScheduleDto;
import com.example.registration.dto.ScheduleDtos.CreateScheduleDto;
import com.example.registration.dto.ScheduleDtos.scheduleDto;
import com.fasterxml.jackson.databind.JsonNode;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
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
    private String sunday;

    @Column(nullable = false)
    private String monday;

    @Column(nullable = false)
    private String tuesday;

    @Column(nullable = false)
    private String wednesday;

    @Column(nullable = false)
    private String thursday;

    @Column(nullable = false)
    private String friday;

    @Column(nullable = false)
    private String saturday;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public Schedule() {
    }

    public Schedule(String email, String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday,
            String saturday) {
        this.email = email;
        this.sunday = sunday;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
    }

    public Schedule(CreateScheduleDto dto) {
        this.email = dto.getEmail();
        this.sunday = dto.getSunday();
        this.monday = dto.getMonday();
        this.tuesday = dto.getTuesday();
        this.wednesday = dto.getWednesday();
        this.thursday = dto.getThursday();
        this.friday = dto.getFriday();
        this.saturday = dto.getSaturday();
    }

    public String getSunday() {
        return sunday;
    }

    public void setSunday(String sunday) {
        this.sunday = sunday;
    }

    public String getMonday() {
        return monday;
    }

    public void setMonday(String monday) {
        this.monday = monday;
    }

    public String getTuesday() {
        return tuesday;
    }

    public void setTuesday(String tuesday) {
        this.tuesday = tuesday;
    }

    public String getWednesday() {
        return wednesday;
    }

    public void setWednesday(String wednesday) {
        this.wednesday = wednesday;
    }

    public String getThursday() {
        return thursday;
    }

    public void setThursday(String thursday) {
        this.thursday = thursday;
    }

    public String getFriday() {
        return friday;
    }

    public void setFriday(String friday) {
        this.friday = friday;
    }

    public String getSaturday() {
        return saturday;
    }

    public void setSaturday(String saturday) {
        this.saturday = saturday;
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
}
