package com.tdd.restfulwebservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tdd.restfulwebservice.entity.User;
import com.tdd.restfulwebservice.exception.UserNotFoundException;
import com.tdd.restfulwebservice.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	public UserRepository userRepository;

	public UserService() {
	}

	public List<User> retrieveAllUsers() {
		return userRepository.findAll();
	}

	public Optional<User> retrieveUser(int id) {
		Optional<User> user = userRepository.findById(id);
		if (!user.isPresent()) {
			throw new UserNotFoundException("id is: " + id);
		}
		return user;
	}

	public User createUser(User user) {
		
		User newUser = null;
		
		if (user.getName()!= null) {
			newUser = userRepository.save(user);
		}
		return newUser;
	}

	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

	

}