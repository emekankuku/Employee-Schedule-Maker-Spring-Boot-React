package com.example.registration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.build.Repository.UserRepository;
import com.example.build.Service.UserService;
import com.example.build.dto.AuthenticationDtos.SignupDto;
import com.example.build.model.User;

@SpringBootTest
class UserServiceTests {

	@Autowired
    private UserRepository<User> userRepository;

	@Autowired
	private UserService service;

	//Mock Employee
	SignupDto dto1 = new SignupDto("FirstName1", "LastName1", "email1@gmail.com", "password1", "Employee");
	//Mock Manager
	SignupDto dto2 = new SignupDto("FirstName2", "LastName2", "email2@gmail.com", "password2", "Manager");

	@BeforeEach
	public void saveMockUser(){ //Save mock user for further testing 
		service.saveUser(dto1);
		service.saveUser(dto2);
	}

	@Test
	public void checkUserSaved(){ //Check if user is saved by asserting that the email exists in the database
		String email1 = dto1.getEmail();
		String email2 = dto2.getEmail();
		String notRealEmail = "randomemail@gmail.com";
		assertNotNull(userRepository.findByEmail(email1));
		assertNotNull(userRepository.findByEmail(email2));
		assertNull(userRepository.findByEmail(notRealEmail));
	}

	@Test
	public void checkUserAttributes(){ //Check if user contains its correct attributes when added to the database
		String email1 = dto1.getEmail();
		User user1 = userRepository.findByEmail(email1);
		String firstName1 = user1.getFirstName();
		String lastName1 = user1.getLastName();
		String role1 = user1.getRole();

		String email2 = dto2.getEmail();
		User user2 = userRepository.findByEmail(email2);
		String firstName2 = user2.getFirstName();
		String lastName2 = user2.getLastName();
		String role2 = user2.getRole();


		assertEquals(firstName1, dto1.getFirstName());
		assertEquals(lastName1, dto1.getLastName());
		assertEquals(role1, dto1.getRole());

		assertEquals(firstName2, dto2.getFirstName());
		assertEquals(lastName2, dto2.getLastName());
		assertEquals(role2, dto2.getRole());
	} 

	// @Test
	// public void ensureRoles(){
	// 	String email1 = dto1.getEmail();
	// 	Manager user1 = repo.findByEmail(email1);
	// 	String role1 = user1.getRole();

	// 	String email2 = dto2.getEmail();
	// 	User user2 = repo.findByEmail(email2);
		
	// }

	@AfterEach
		public void deleteMockUser(){ //Delete user to end testing 
			service.deleteUser(dto1.getEmail());
			service.deleteUser(dto2.getEmail());
	}

}
