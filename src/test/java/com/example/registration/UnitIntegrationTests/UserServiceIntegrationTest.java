package com.example.registration.UnitIntegrationTests;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.build.Repository.UserRepository;
import com.example.build.Service.UserService;
import com.example.build.Service.UserServiceImpl;
import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.model.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceIntegrationTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository<User> userRepository;

    // Mock Employee
    SignupDto dto1 = new SignupDto("FirstName1", "LastName1", "email1@gmail.com", "password1", "Employee");
    // Mock Manager
    SignupDto dto2 = new SignupDto("FirstName2", "LastName2", "email2@gmail.com", "password2", "Manager");
    User alex = new User(dto1);

    @Before
    public void setUp() {
        Mockito.when(userRepository.findByEmail(alex.getEmail())).thenReturn(alex);
        Mockito.when(userRepository.save(alex)).thenReturn(alex);
    }

    @Test
    public void whenValidEmail_thenUserShouldBeFound() {
        String email = dto1.getEmail();
        User found = userService.findByEmail(email);
        User saved = userService.saveUser(dto1);
        assertTrue(found.getEmail().equals(email));
        assertTrue(saved == alex);
    }

}
