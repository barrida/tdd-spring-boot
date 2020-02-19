package com.tdd.restfulwebservice.service;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.time.Month;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.tdd.restfulwebservice.entity.User;
import com.tdd.restfulwebservice.repository.UserRepository;
import com.tdd.restfulwebservice.service.UserService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserServiceUnitTest {

	@Mock
	private UserRepository userRepository;

	@InjectMocks
	private UserService userService;

	//We need this if we don't use @RunWith(MockitoJUnitRunner.class)
	//	@Before
	//	public void setup() {
	//		MockitoAnnotations.initMocks(this);
	//	}

	@Test
	public void testRetrieveAllUsersHappyPath() {
		// Create a user
		User aMockUser = new User();
		aMockUser.setName("DummyName");
		aMockUser.setBirthDate(LocalDate.of(2000, 4, 1));

		when(userRepository.save(any(User.class))).thenReturn(aMockUser);

		// Save the user
		User newUser = userService.createUser(aMockUser);

		// Verify the save
		assertEquals("DummyName", newUser.getName());
	}
}
