package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.UserWithoutPass;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.service.UserService;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/users")
@CrossOrigin(value = "*")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserWithoutPass> getAllUsers(){
        List<UserWithoutPass> usersDto = new ArrayList<>();
        List<User> users = userService.findAll();
        for (User user: users){
            usersDto.add(UserWithoutPass.fromUser(user));
        }
        return usersDto;
    }

    @GetMapping("/{id}")
    public UserWithoutPass getUserById(@PathVariable Integer id){
        return UserWithoutPass.fromUser(userService.findById(id));
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public UserWithoutPass saveUser(@RequestBody @Valid User user){
        return UserWithoutPass.fromUser(userService.save(user));
    }

    @PutMapping
    public UserWithoutPass updateUser(@RequestBody @Valid User user){
        return UserWithoutPass.fromUser(userService.update(user, user.getId()));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteUserById(@PathVariable Integer id){
        userService.deleteById(id);
    }

    @PatchMapping("/{id}")
    public UserWithoutPass partialUpdate(@PathVariable Integer id, @RequestBody Map<String,Object> source){
        return UserWithoutPass.fromUser(userService.partialUpdate(source, id));
    }

}
