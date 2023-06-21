package com.example.registration.dto.GroupDtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class addUserDto {

    @NotNull(message = "Group name is required")
    @Size(min = 1, message = "Group name is required")
    private String groupName;

    private String managerEmail;

    private String employeeEmail;

    public addUserDto() {

    }

    public addUserDto(String groupName, String managerEmail, String employeeEmail) {
        this.groupName = groupName;
        this.managerEmail = managerEmail;
        this.employeeEmail = employeeEmail;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getManagerEmail() {
        return managerEmail;
    }

    public void setManagerEmail(String managerEmail) {
        this.managerEmail = managerEmail;
    }

    public String getEmployeeEmail() {
        return employeeEmail;
    }

    public void setEmployeeEmail(String employeeEmail) {
        this.employeeEmail = employeeEmail;
    }

}
