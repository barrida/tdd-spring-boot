package com.tdd.restfulwebservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tdd.restfulwebservice.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByname(String name);
}
