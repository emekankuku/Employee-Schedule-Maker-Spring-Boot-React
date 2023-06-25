package com.example.build.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.build.Service.GroupService;
import com.example.build.dto.UserDto;
import com.example.build.dto.DaysOffDtos.DaysOffOutputDto;
import com.example.build.dto.GroupDtos.CreateGroupDto;
import com.example.build.dto.GroupDtos.GroupDto;
import com.example.build.dto.GroupDtos.addUserDto;
import com.example.build.dto.ScheduleDtos.scheduleDto;

@RestController
@CrossOrigin(origins = "http://localhost:3000", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("/grouping")
public class GroupController {

    @Autowired
    private GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/createGroup")
    public GroupDto createGroup(@RequestBody CreateGroupDto dto) {
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
