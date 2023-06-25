package com.example.build.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.build.Service.ScheduleService;
import com.example.build.dto.DaysOffDtos.daysOffDto;
import com.example.build.dto.ScheduleDtos.CreateScheduleDto;
import com.example.build.dto.ScheduleDtos.deleteScheduleDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/createSchedule")
    public scheduleDto createSchedule(@Valid @RequestBody CreateScheduleDto dto) {
        return scheduleService.createSchedule(dto);
    }

    @PostMapping("/createDaysOff")
    public daysOffDto createDaysOff(@Valid @RequestBody daysOffDto dto) {
        return scheduleService.createDaysOff(dto);
    }

    @PostMapping("/deleteSchedule")
    public String deleteSchedule(@RequestBody deleteScheduleDto dto) {
        return scheduleService.deleteSchedule(dto);
    }

    @PostMapping("/deleteDaysOff")
    public String deleteDaysOff(@RequestBody deleteScheduleDto dto) {
        return scheduleService.deleteDaysOff(dto);
    }

}
