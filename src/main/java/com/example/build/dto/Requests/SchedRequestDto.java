package com.example.build.dto.Requests;

import com.example.build.dto.ScheduleDtos.scheduleDto;
import com.example.build.model.Requests.SchedRequest;

public class SchedRequestDto extends scheduleDto{
    
    private String name;

    private String email;

    private String group;

    public SchedRequestDto(){

    }

    public SchedRequestDto(SchedRequest request){
        super(request);
        this.name = request.getFullName();
        this.email = request.getEmail();
        this.group = request.getGroupName();
    }

    public SchedRequestDto(String group){
        this.group = group;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
}
