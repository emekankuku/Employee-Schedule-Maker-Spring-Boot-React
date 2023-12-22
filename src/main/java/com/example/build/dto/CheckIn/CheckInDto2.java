package com.example.build.dto.CheckIn;

import com.example.build.model.CheckIn;
import java.time.LocalDateTime;

public class CheckInDto2 {

    private String date;

    private String time;

    private String inOrOut;

    public CheckInDto2() {

    }

    public CheckInDto2(CheckIn checkIn) {
        LocalDateTime date = checkIn.getLt();
        this.date = date.getMonth() + " " + date.getDayOfMonth() + ", " + date.getYear();
        this.time = date.getHour() + ":" + date.getMinute();
        this.inOrOut = checkIn.getInOrOut();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(String inOrOut) {
        this.inOrOut = inOrOut;
    }

}
