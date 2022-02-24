package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Integer id){
        return userService.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public User saveUser(@RequestBody @Valid User user){
        return userService.save(user);
    }

    @PutMapping
    public User updateUser(@RequestBody @Valid User user){
        return userService.update(user, user.getId());
    }

    @DeleteMapping
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserById(Integer id){
        userService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public User partialUpdate(@PathVariable Integer id, @RequestBody User user){
        return userService.partialUpdate(user, id);
    }

}
