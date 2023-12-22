package com.example.registration.UnitIntegrationTests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.build.Repository.DaysOffRepository;
import com.example.build.Repository.GroupRepository;
import com.example.build.Repository.ScheduleRepository;
import com.example.build.Repository.UserRepository;
import com.example.build.Service.GroupService;
import com.example.build.Service.ScheduleService;
import com.example.build.Service.UserServiceImpl;
import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.dto.GroupDtos.GroupDto;
import com.example.build.dto.GroupDtos.addUserDto;
import com.example.build.exceptions.DuplicateUserException;
import com.example.build.dto.GroupDtos.CreateGroupDto;
import com.example.build.model.Group;
import com.example.build.model.User;

@SpringBootTest
public class GroupTests {

    @Autowired
    private UserRepository<User> userRepository;

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private DaysOffRepository daysOffRepository;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private ScheduleService scheduleService;

    // Mock Employee 1
    SignupDto employeeDto1 = new SignupDto("FirstName1", "LastName1", "email1@gmail.com", "password1", "Employee");
    // Mock Employee 2
    SignupDto employeeDto2 = new SignupDto("FirstName2", "LastName2", "email2@gmail.com", "password2", "Employee");
    // Mock Manager
    SignupDto managerDto = new SignupDto("FirstName3", "LastName3", "email3@gmail.com", "password3", "Manager");

    @BeforeEach
    public void start() { // Save mock user for further testing
        daysOffRepository.deleteAll();
        scheduleRepository.deleteAll();
        userRepository.deleteAll();
        groupRepository.deleteAll();
        userService.saveUser(employeeDto1);
        userService.saveUser(employeeDto2);
        userService.saveUser(managerDto);
    }

    @Test
    public void checkGroupCreationAuthorization() { // Checks authorization of creating a group
        CreateGroupDto dto1 = new CreateGroupDto("group1", managerDto.getEmail());

        CreateGroupDto dto2 = new CreateGroupDto("group2", employeeDto1.getEmail());

        assertDoesNotThrow(() -> {
            groupService.createGroup(dto1);
        });

        Exception exception1 = assertThrows(DuplicateUserException.class, () -> {
            groupService.createGroup(dto2);
        });

        assertNotNull(exception1);
        assertNotNull(groupRepository.findByName(dto1.getName()));
        assertNull(groupRepository.findByName(dto2.getName()));
    }

    @Test
    public void checkAddUserAuthorization() { // Check authorization of adding a group
        String groupName1 = "group1";
        String groupName2 = "group2";

        User employee2 = userRepository.findByEmail(employeeDto2.getEmail()); 

        groupService.createGroup(new CreateGroupDto(groupName1, managerDto.getEmail()));
        groupService.createGroup(new CreateGroupDto(groupName2, managerDto.getEmail()));

        addUserDto dto1 = new addUserDto(groupName1, managerDto.getEmail(), employeeDto2.getEmail());
        addUserDto dto2 = new addUserDto(groupName2, employeeDto1.getEmail(), employeeDto2.getEmail());

        Group group1 = groupRepository.findByName(groupName1);
        Group group2 = groupRepository.findByName(groupName2);

        assertDoesNotThrow(() -> { //Check that manager adding user does not throw exception
            groupService.addUser(dto1);
        });

        assertTrue(group1.getUsers().contains(employee2));

        Exception exception1 = assertThrows(DuplicateUserException.class, () -> {
            groupService.addUser(dto2);
        });

        assertNotNull(exception1);

        
    }

    @AfterEach
    public void end() { // Save mock user for further testing
        daysOffRepository.deleteAll();
        scheduleRepository.deleteAll();
        userRepository.deleteAll();
        groupRepository.deleteAll();
    }
}
