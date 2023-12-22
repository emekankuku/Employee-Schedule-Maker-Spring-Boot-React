package com.example.build.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String leader;

    //This maps a group entity's users to still exist after the former is deleted
    @ManyToMany
    @JoinTable(name = "group_to_user", joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private Set<User> users = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<Schedule> schedules = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<DaysOff> daysOff = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy = "group")
    private Set<CheckIn> checkIns = new HashSet<>();

    public Group() {
    }

    public Group(String name) {
        this.name = name;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void removeUser(User user){
        users.remove(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public Set<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(Set<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule){
        schedules.add(schedule);
    }

    public void removeSchedule(Schedule schedule){
        schedules.remove(schedule);
    }

    public Set<DaysOff> getDaysOff() {
        return daysOff;
    }

    public void setDaysOff(Set<DaysOff> daysOff) {
        this.daysOff = daysOff;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }
    
    


}
