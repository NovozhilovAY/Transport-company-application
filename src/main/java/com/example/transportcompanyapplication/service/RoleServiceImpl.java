package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.model.Role;
import com.example.transportcompanyapplication.repository.RoleRepository;
import com.example.transportcompanyapplication.service.api.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private final RoleRepository repository;

    public RoleServiceImpl(RoleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Role> findAll(){
        return repository.findAll();
    }
}
