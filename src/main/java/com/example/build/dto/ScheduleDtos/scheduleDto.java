package com.example.build.dto.ScheduleDtos;

import com.example.build.model.Schedule;
import com.example.build.model.Requests.SchedRequest;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class scheduleDto {

    final String timeRequired = "Time is required";
    //Below regex indicates that time variables can either be an empty string or follow 'hh:mm-hh:mm'
    final String regex = "(^$|^([0-1]?[0-9]|2[0-3]):[0-5][0-9]-([0-1]?[0-9]|2[0-3]):[0-5][0-9]$)";
    final String incorrect = "Incorrect Time Range Format";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Sun = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Mon = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Tue = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Wed = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Thur = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Fri = "";

    // @NotNull(message = timeRequired)
    // @Size(min = 1, message = timeRequired)
    @Pattern(regexp = regex, message = incorrect)
    private String Sat = "";

    public scheduleDto() {
    }

    public scheduleDto(String sunday, String monday, String tuesday, String wednesday, String thursday,
            String friday, String saturday) {
        Sun = sunday;
        Mon = monday;
        Tue = tuesday;
        Wed = wednesday;
        Thur = thursday;
        Fri = friday;
        Sat = saturday;
    }

    public scheduleDto(Schedule dto) {
        Sun = dto.getSunday();
        Mon = dto.getMonday();
        Tue = dto.getTuesday();
        Wed = dto.getWednesday();
        Thur = dto.getThursday();
        Fri = dto.getFriday();
        Sat = dto.getSaturday();
    }

    public scheduleDto(SchedRequest dto) {
        Sun = dto.getSunday();
        Mon = dto.getMonday();
        Tue = dto.getTuesday();
        Wed = dto.getWednesday();
        Thur = dto.getThursday();
        Fri = dto.getFriday();
        Sat = dto.getSaturday();
    }

   

public String getSun() {
    return Sun;
}

public void setSun(String sun) {
    Sun = sun;
}

public String getMon() {
    return Mon;
}

public void setMon(String mon) {
    Mon = mon;
}

public String getTue() {
    return Tue;
}

public void setTue(String tue) {
    Tue = tue;
}

public String getWed() {
    return Wed;
}

public void setWed(String wed) {
    Wed = wed;
}

public String getThur() {
    return Thur;
}

public void setThur(String thur) {
    Thur = thur;
}

public String getFri() {
    return Fri;
}

public void setFri(String fri) {
    Fri = fri;
}

public String getSat() {
    return Sat;
}

public void setSat(String sat) {
    Sat = sat;
} 

}
