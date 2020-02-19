package com.tdd.restfulwebservice.service;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import com.tdd.restfulwebservice.entity.User;
import com.tdd.restfulwebservice.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreateUserHappyPath() {
		// Create a user
		User aMockUser = new User();
		aMockUser.setName("DummyName");
		aMockUser.setBirthDate(LocalDate.of(2000, 4, 1));
		
		// Test adding the user
		User newUser = userService.createUser(aMockUser);

		// Verify the addition
		assertNotNull(newUser);
		assertNotNull(newUser.getId());
		assertEquals("DummyName", newUser.getName());
	}

}
