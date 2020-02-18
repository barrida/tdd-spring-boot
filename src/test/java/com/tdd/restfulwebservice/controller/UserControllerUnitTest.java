package com.tdd.restfulwebservice.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.tdd.restfulwebservice.controller.UserController;
import com.tdd.restfulwebservice.entity.User;
import com.tdd.restfulwebservice.service.UserService;


@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerUnitTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;
	
	@InjectMocks
	private UserController userController;

	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);	
	}
	
	@Test
	public void testCreateUserHappyPath() throws Exception {
		// setup mock User returned the mock service component
		User aMockUser = new User();
		aMockUser.setName("DummyName");
		aMockUser.setBirthDate(LocalDate.of(2000, 4, 1));
				
		when(userService.createUser(any(User.class)))
			.thenReturn(aMockUser);
		
		String requestBody = "{\"id\":null, \"name\":\"DummyName\",\"birthDate\":\"2016-02-11\"}";
	
		// simulate the form submit (POST)
		mockMvc.perform(post("/users")
				.contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(aMockUser))
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();
	}
	

	/**
	 * Serializing LocalDate to JSON 
	 * 
	 * @param obj
	 * @return
	 */
	public static String asJsonString(final Object obj) {
	    try {
	    	ObjectMapper mapper = new ObjectMapper();
	    	mapper.registerModule(new JavaTimeModule());
	    	mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
	    	return mapper.writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
