package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ExtendedRepository<User, Integer> {
    Optional<User> findByLogin(String login);
}
