package com.example.transportcompanyapplication.service;


import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Role;
import com.example.transportcompanyapplication.model.User;
import com.example.transportcompanyapplication.repository.UserRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Map;


@Service
public class UserService extends AbstractService<User, Integer>{
    private final PasswordEncoder passwordEncoder;
    public UserService(UserRepository userRepository, PatchMapper<User> mapper, PasswordEncoder passwordEncoder) {
        super(userRepository, mapper);
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(User user) {
        this.encodePassword(user);
        return super.save(user);
    }

    @Override
    public User update(User user, Integer id) {
        this.encodePassword(user);
        return super.update(user, id);
    }

    @Override
    public User partialUpdate(Map<String, Object> source, Integer id) {
        User updatedUser = super.findById(id);
        String oldPass = updatedUser.getPassword();
        if(source.containsKey("roles") && source.get("roles") != null){
            source.put("roles", new ObjectMapper().convertValue(source.get("roles"), new TypeReference<List<Role>>() {}));
        }
        mapper.update(source, updatedUser);
        if(!updatedUser.getPassword().equals(oldPass)){
            encodePassword(updatedUser);
        }
        return super.update(updatedUser,id);
    }

    public User getByLogin(String login){
        return ((UserRepository)repository).findByLogin(login).orElseThrow(
                ()-> new ResourceNotFoundException("User with login = " + login + " not found!")
        );
    }

    private void encodePassword(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
    }
}
