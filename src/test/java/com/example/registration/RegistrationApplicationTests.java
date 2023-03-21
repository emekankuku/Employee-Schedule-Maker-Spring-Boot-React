package com.example.registration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.UserService;
import com.example.registration.dto.UserRegistrationDto;

@SpringBootTest
class RegistrationApplicationTests {
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private UserService service;

	@Test
	void contextLoads() {
	}

	@Test
	void userSaved(){
		UserRegistrationDto dto = new UserRegistrationDto("First2", "last2", "email2", "password2");
		service.saveUser(dto);
		
		
	}

}
