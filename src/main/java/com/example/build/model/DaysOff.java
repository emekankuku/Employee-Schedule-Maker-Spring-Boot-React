package com.example.build.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.example.build.dto.DaysOffDtos.daysOffDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "daysOff")
public class DaysOff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String note;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;

    public DaysOff() {
    }

    public DaysOff(String email, LocalDate startDate, LocalDate endDate, String note) {
        this.email = email;
        this.startDate = startDate;
        this.endDate = endDate;
        this.note = note;
    }

    public DaysOff(daysOffDto dto) {
        this.email = dto.getEmail();
        this.startDate = LocalDate.parse(dto.getStartDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.endDate = LocalDate.parse(dto.getEndDate(), DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        this.note = dto.getNote();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

}
