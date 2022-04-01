package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends ExtendedRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    @Query(value = "SELECT count(*)" +
            " FROM user_roles " +
            "JOIN roles r on r.id = user_roles.role_id " +
            "JOIN users u on u.id = user_roles.user_id " +
            "WHERE r.name = 'ROLE_ADMIN'", nativeQuery = true)
    Integer numberOfAdmins();
}
