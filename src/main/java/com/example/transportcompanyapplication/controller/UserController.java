package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id)throws ResourceNotFoundException {
        return userService.findById(id);
    }

    @PostMapping
    public User saveUser(@RequestBody User user){
        return userService.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody User user) throws ResourceNotFoundException {
        return userService.update(user);
    }

    @DeleteMapping
    public ResponseEntity<Response> deleteUserById(Integer id) throws ResourceNotFoundException{
        return userService.deleteById(id);
    }

}
