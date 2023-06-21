package com.example.registration.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.registration.Repository.GroupRepository;
import com.example.registration.Repository.ScheduleRepository;
import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.GroupService;
import com.example.registration.dto.EmailDto;
import com.example.registration.dto.UserDto;
import com.example.registration.dto.DaysOffDtos.DaysOffOutputDto;
import com.example.registration.dto.DaysOffDtos.daysOffDto;
import com.example.registration.dto.GroupDtos.GroupDto;
import com.example.registration.dto.GroupDtos.UtoGDto;
import com.example.registration.dto.GroupDtos.addScheduleDto;
import com.example.registration.dto.GroupDtos.addUserDto;
import com.example.registration.dto.ScheduleDtos.scheduleDto;
import com.example.registration.exceptions.DuplicateGroupException;
import com.example.registration.exceptions.DuplicateUserException;
import com.example.registration.model.Group;
import com.example.registration.model.Schedule;
import com.example.registration.model.User;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/grouping")
public class GroupController {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/createGroup")
    public GroupDto createGroup(@RequestBody UtoGDto dto) {
        return groupService.createGroup(dto);
    }

    @PostMapping("/addUser")
    public GroupDto addUser(@RequestBody addUserDto dto) {
        return groupService.addUser(dto);
    }

    @PostMapping("/getGroups")
    public List<GroupDto> getGroups(@RequestBody UserDto dto) {
        return groupService.getGroups(dto);
    }

    @PostMapping("/getUsers")
    public List<UserDto> getUsers(@RequestBody GroupDto dto) {
        return groupService.getUsers(dto);
    }

    @PostMapping("/getSchedules")
    public List<scheduleDto> getSchedules(@RequestBody GroupDto dto) {
        return groupService.getSchedules(dto);
    }

    @PostMapping("/getDaysOff")
    public List<DaysOffOutputDto> getDaysOff(@RequestBody GroupDto dto) {
        return groupService.getDaysOff(dto);
    }

    @PostMapping("/deleteGroup")
    public String deleteGroup(@RequestBody GroupDto dto) {
        return groupService.deleteGroup(dto);
    }
}
