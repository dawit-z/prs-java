package com.maxtrain.bootcamp.prs.repository;

import com.maxtrain.bootcamp.prs.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
Optional<User> findByUsernameAndPassword(String username, String password);
}
