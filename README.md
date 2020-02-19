# Introduction

A base project for RESTful Web Service using Test-Driven Development. You can clone and modify this project in order to save time.

# Technologies Used

Java 8  
Spring Boot 2.2.4.RELEASE  
Spring HATEAOS 1.x  
JUnit 4  
Mockito  
H2 Database  
Swagger  
Lombok 

# Testing Approach 

There are Integration and Unit Tests for each component of our the application. It is a good idea to start writing integration tests when you have all the components ready. We test the interaction between units. On the other hand, when we need to add a new feature to the application, unit test is the way to go. 

### Testing Service Components

#### Unit Test

We use @RunWith and @SpringBootTest annotations for unit test class. We don't load the controller but load the service and its dependencies. @SpringBootTest(webEnvironment = WebEnvironment.NONE) mode enables that the application should not run as a web application and should not start an embedded web server. @RunWith(MockitoJUnitRunner.class) initializes the Mock objects, which is UserRepository.

	@RunWith(MockitoJUnitRunner.class)
	@SpringBootTest(webEnvironment = WebEnvironment.NONE)
	public class UserServiceUnitTest { 
		... 
	}

@Mock annotation is used to inject the repository. We mock the data as we don't care about the actual data.

	@Mock
	private UserRepository userRepository;

@InjectMocks annotation is used to inject the service

	@InjectMocks
	private UserService userService;

@Before annotation causes that method to be run before each @Test method. If we didn't use @RunWith(MockitoJUnitRunner.class) in the class definition, we had to call MockitoAnnotations.initMocks(this) method to initialize annotated fields. 

	@SpringBootTest(webEnvironment = WebEnvironment.NONE)
	public class UserServiceUnitTest { 
		... 
		@Before
		public void setup() {
			MockitoAnnotations.initMocks(this);
		}
		...
	}

Here is how we test the service and verify the result.

	// Create a user
	User aMockUser = new User();
	... set fields here

	when(userRepository.save(any(User.class))).thenReturn(aMockUser);

	// Save the user
	User newUser = userService.createUser(aMockUser);

	// Verify the save
	assertEquals("DummyName", newUser.getName());

#### Integration Test

Integration test involves the interaction between the UserService and UserRepository. We don't want to run any of the controllers as we only want to test the service component. We just want to access the service and data access components. @SpringBootTest(webEnvironment = WebEnvironment.NONE) mode enables that the application should not run as a web application and should not start an embedded web server. We also add @RunWith(SpringRunner.class) to our test, otherwise the annotations will be ignored.


	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = WebEnvironment.NONE)
	public class UserServiceIntegrationTest { 
		... 
	}

@Autowired annotation injects the service that we want to test

	@Autowired
	private UserService userService;
	
Now we can test the UserService straight away

	// Create User
	User aMockUser = new User();
	... set fields here
	
	// Test adding the user
	User newUser = userService.createUser(aMockUser);

	// Verify the addition
	assertNotNull(newUser);

### Testing Controllers

#### Unit Test

@WebMvcTest only scans the controller that we want to test

	@RunWith(SpringRunner.class)
	@WebMvcTest(value = UserController.class)
	public class UserControllerUnitTest {
		...
	}
	
We inject the MockMvc to perform a request and the UserService in order to stub the the user service

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserService userService;

Test the controller method, using MockMvc object.
		
	// simulate the form submit (POST)
	mockMvc.perform(post("/users")
			.contentType(MediaType.APPLICATION_JSON)
			.content(asJsonString(aMockUser))
			.accept(MediaType.APPLICATION_JSON))
			.andExpect(status().isCreated())
			.andReturn();

#### Integration Test

Since we want to simulate the real environment, we use WebEnvironment.RANDOM_PORT to create a web application context (which usually triggers listening on a random port).

	@RunWith(SpringRunner.class)
	@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
	public class UserControllerIntegrationTest {
		...
	}


We inject the controller

	@Autowired
	UserController userController;

We use the contoller straight away and verify the result

	@Test
	public void testCreateUserHappyPath() {
		User aMockUser = new User();
		..set fields here
		
		// POST our User form bean to the controller; check the outcome
		ResponseEntity<User> newUser = userController.createUser(aMockUser);

		// Assert that the outcome is as expected
		assertThat(newUser.getStatusCode(), is(equalTo(HttpStatus.CREATED)));
	}


### Testing Repository

#### Integration Test


### Create Test Suites


# Swagger

# Resources

[Spring: Test-Driven Development with JUnit](https://www.linkedin.com/learning/spring-test-driven-development-with-junit)  
[Master Microservices with Spring Boot and Spring Cloud](https://www.udemy.com/course/microservices-with-spring-boot-and-spring-cloud)  
[Spring HATEOAS - Reference Documentation](https://docs.spring.io/spring-hateoas/docs/current/reference/html/)  
[Spring HATEOAS Tutorial](https://howtodoinjava.com/spring5/hateoas/spring-hateoas-tutorial)  
[Swagger Spring Boot 2.2.x - Bug Fix](https://stackoverflow.com/questions/58626347/springfox-swagger-not-working-in-spring-boot-2-2-0)