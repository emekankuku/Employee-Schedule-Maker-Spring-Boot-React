// package com.example.registration;

// import static org.junit.Assert.assertNotNull;
// import static org.junit.Assert.assertNull;

// import org.junit.jupiter.api.AfterEach;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.context.SpringBootTest;

// import com.example.registration.Repository.DaysOffRepository;
// import com.example.registration.Repository.GroupRepository;
// import com.example.registration.Repository.ScheduleRepository;
// import com.example.registration.Repository.UserRepository;
// import com.example.registration.Service.GroupService;
// import com.example.registration.Service.ScheduleService;
// import com.example.registration.Service.UserServiceImpl;
// import com.example.registration.dto.AuthenticationDtos.SignupDto;
// import com.example.registration.dto.GroupDtos.GroupDto;
// import com.example.registration.dto.GroupDtos.UtoGDto;
// import com.example.registration.model.User;

// @SpringBootTest
// public class GroupTests {
    
//     @Autowired
//     private UserRepository<User> userRepository;

//     @Autowired
//     private GroupRepository groupRepository;

//     @Autowired
//     private ScheduleRepository scheduleRepository;

//     @Autowired
//     private DaysOffRepository daysOffRepository;

//     @Autowired
//     private UserServiceImpl userService;

//     @Autowired
//     private GroupService groupService;

//     @Autowired
//     private ScheduleService scheduleService;

//     //Mock Employee
// 	SignupDto employeeDto1 = new SignupDto("FirstName1", "LastName1", "email1@gmail.com", "password1", "Employee");
// 	//Mock Manager
// 	SignupDto managerDto = new SignupDto("FirstName2", "LastName2", "email2@gmail.com", "password2", "Manager");

//     @BeforeEach
// 	public void start(){ //Save mock user for further testing 
//         userRepository.deleteAll();
//         groupRepository.deleteAll();
//         scheduleRepository.deleteAll();
//         daysOffRepository.deleteAll();
//         userService.saveUser(employeeDto1);
// 		userService.saveUser(managerDto);
// 	}



//     @Test
// 	public void checkGroupCreationAuthorization(){ //Check if user is saved by asserting that the email exists in the database
// 		UtoGDto dto1 = new UtoGDto("group1", managerDto.getEmail());
//         UtoGDto dto2 = new UtoGDto("group2", employeeDto1.getEmail());

//         groupService.createGroup(dto1);
//         groupService.createGroup(dto2);

//         assertNotNull(groupRepository.findByName(dto1.getGroupName()));
//         assertNull(groupRepository.findByName(dto2.getGroupName()));
// 	}

//     @AfterEach
// 	public void end(){ //Save mock user for further testing 
//         userRepository.deleteAll();
//         groupRepository.deleteAll();
//         scheduleRepository.deleteAll();
//         daysOffRepository.deleteAll();
// 	}
// }
