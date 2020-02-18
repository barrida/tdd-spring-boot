package com.tdd.restfulwebservice.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.tdd.restfulwebservice.entity.User;
import com.tdd.restfulwebservice.repository.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class UserRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private UserRepository userRepository;

	@Test
	public void testFindBynameHappyPath() {
		// setup data scenario
		User aMockUser = new User();
		aMockUser.setName("DummyName");
		aMockUser.setBirthDate(LocalDate.of(2000, 4, 1));

		// save test data
		entityManager.persist(aMockUser);

		// Find an inserted record
		User foundUser = userRepository.findByname("DummyName");

		assertThat(foundUser.getName(), is(equalTo("DummyName")));
		assertThat(foundUser.getBirthDate().toString(), is(equalTo("2000-04-01")));
	}

}
