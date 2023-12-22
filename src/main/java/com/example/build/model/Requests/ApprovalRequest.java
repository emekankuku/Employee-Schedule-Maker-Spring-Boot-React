package com.example.build.model.Requests;

import com.example.build.dto.ScheduleDtos.CreateScheduleDto;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
public class ApprovalRequest extends Request{
    
    @Column(nullable=false)
    private String message;

    public ApprovalRequest(){

    }

    public ApprovalRequest(String message){
        this.message = message;
    }

    public ApprovalRequest(String receiver, String message){
        super(receiver);
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
