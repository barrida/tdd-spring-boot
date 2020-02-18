package com.tdd.restfulwebservice;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tdd.restfulwebservice.controller.UserControllerIntegrationTest;
import com.tdd.restfulwebservice.repository.UserRepositoryIntegrationTest;
import com.tdd.restfulwebservice.service.UserServiceIntegrationTest;
import com.tdd.restfulwebservice.service.UserServiceUnitTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ UserServiceIntegrationTest.class,
		UserRepositoryIntegrationTest.class, UserControllerIntegrationTest.class })
public class CreateUserFeatureTestSuite {
	
	// intentionally empty - Test Suite setup (annotations) is sufficient 
	
}
