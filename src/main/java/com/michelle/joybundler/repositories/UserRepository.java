package com.michelle.joybundler.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.michelle.joybundler.models.User;



public interface UserRepository extends CrudRepository<User, Long> {
	Optional<User> findByEmail(String email);
}

