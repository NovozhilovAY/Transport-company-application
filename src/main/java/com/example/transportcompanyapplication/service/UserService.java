package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService extends AbstractService<User, Integer>{
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PatchMapper<User> mapper, PasswordEncoder passwordEncoder) {
        super(userRepository, mapper);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user, Integer id) {
        this.encodePassword(user);
        return super.save(user, id);
    }

    @Override
    public User update(User user, Integer id) {
        this.encodePassword(user);
        return super.update(user, id);
    }

    @Override
    public User partialUpdate(User user, Integer id) {
        User updatedUser = super.findById(id);
        String oldPass = updatedUser.getPassword();
        String newPass = user.getPassword();
        mapper.update(user, updatedUser);
        if(newPass != null &&
                !passwordEncoder.encode(newPass).equals(oldPass)){
            updatedUser.setPassword(passwordEncoder.encode(newPass));
        }
        return super.update(user,id);
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
