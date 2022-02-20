package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id){
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User with id = " + id + " not found")
        );
    }

    public User save(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user){
        User updatedUser = this.findById(user.getId());
        return this.save(user);
    }

    public void deleteById(Integer id){
        this.findById(id);
        userRepository.deleteById(id);
    }
}
