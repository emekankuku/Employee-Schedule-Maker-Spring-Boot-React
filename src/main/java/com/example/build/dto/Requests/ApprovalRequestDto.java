package com.example.build.dto.Requests;

public class ApprovalRequestDto {
    
    private String message;

    public ApprovalRequestDto(){

    }

    public ApprovalRequestDto(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
}
