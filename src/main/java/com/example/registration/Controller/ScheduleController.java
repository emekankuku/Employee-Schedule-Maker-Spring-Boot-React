package com.example.registration.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.registration.Repository.DaysOffRepository;
import com.example.registration.Repository.GroupRepository;
import com.example.registration.Repository.ScheduleRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.GroupService;
import com.example.registration.Service.ScheduleService;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.DaysOffDtos.daysOffDto;
import com.example.registration.dto.GroupDtos.GroupDto;
import com.example.registration.dto.GroupDtos.UtoGDto;
import com.example.registration.dto.GroupDtos.addScheduleDto;
import com.example.registration.dto.GroupDtos.addUserDto;
import com.example.registration.dto.ScheduleDtos.CreateScheduleDto;
import com.example.registration.dto.ScheduleDtos.deleteScheduleDto;
import com.example.registration.dto.ScheduleDtos.scheduleDto;
import com.example.registration.exceptions.DuplicateGroupException;
import com.example.registration.exceptions.DuplicateUserException;
import com.example.registration.model.DaysOff;
import com.example.registration.model.Group;
import com.example.registration.model.Schedule;
import com.example.registration.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

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
    public daysOffDto createDaysOff(@RequestBody daysOffDto dto) {
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
