package com.example.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.registration.Repository.UserRepository;
import com.example.registration.Service.UserService;
import com.example.registration.dto.SignupDto;
import com.example.registration.model.User;

@SpringBootTest
class UserServiceTests {

	@Autowired
	private UserRepository repo;

	@Autowired
	private UserService service;

	SignupDto dto = new SignupDto("FirstName", "LastName", "emaill@gmail.com", "password");

	@BeforeEach
	public void saveMockUser(){
		service.saveUser(dto);
	}

	@Test
	public void checkUserSaved(){
		String email = dto.getEmail();
		String notRealEmail = "randomemail@gmail.com";
		assertNotNull(repo.findByEmail(email));
		assertNull(repo.findByEmail(notRealEmail));
	}

	@Test
	public void checkUserAttributes(){
		String email = dto.getEmail();
		User user = repo.findByEmail(email);
		String firstName = user.getFirstName();
		String lastName = user.getLastName();

		assertEquals(firstName, dto.getFirstName());
		assertEquals(lastName, dto.getLastName());
	} 

	@AfterEach
		public void deleteMockUser(){
			service.deleteUser(dto.getEmail());
	}

}
