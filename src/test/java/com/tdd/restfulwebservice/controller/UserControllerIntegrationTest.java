package com.tdd.restfulwebservice.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.tdd.restfulwebservice.controller.UserController;
import com.tdd.restfulwebservice.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class UserControllerIntegrationTest {

	@Autowired
	UserController userController;

	@Test
	public void testCreateUserHappyPath() {
		User aMockUser = new User();
		aMockUser.setName("DummyName");
		aMockUser.setBirthDate(LocalDate.of(2000, 4, 1));

		// POST our User form bean to the controller; check the outcome
		ResponseEntity<User> newUser = userController.createUser(aMockUser);

		// Assert THAT the outcome is as expected
		assertThat(newUser.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
	}

	@Test
	public void testCreateUserNameAndBirthdateMissing() {
		// implement this
	}
}
