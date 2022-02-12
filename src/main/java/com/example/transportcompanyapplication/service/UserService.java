package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public User findById(Integer id) throws ResourceNotFoundException {
        return userRepository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("User with id = " + id + " not found")
        );
    }

    public User save(User user){
        return userRepository.save(user);
    }

    public User update(User user) throws ResourceNotFoundException {
        User updatedUser = this.findById(user.getId());
        return userRepository.save(user);
    }

    public ResponseEntity<Response> deleteById(Integer id) throws ResourceNotFoundException {
        this.findById(id);
        return new ResponseEntity<>(new Response("User with id = " + id + " was deleted"), HttpStatus.OK);
    }
}
