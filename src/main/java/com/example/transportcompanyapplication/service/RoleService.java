package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.model.Role;
import com.example.transportcompanyapplication.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository repository;

    public RoleService(RoleRepository repository) {
        this.repository = repository;
    }

    public List<Role> findAll(){
        return repository.findAll();
    }
}
