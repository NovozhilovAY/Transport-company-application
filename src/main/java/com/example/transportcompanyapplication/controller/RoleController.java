package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.model.Role;
import com.example.transportcompanyapplication.service.RoleService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/roles")
public class RoleController {
    private final RoleService service;

    public RoleController(RoleService service) {
        this.service = service;
    }

    @GetMapping
    public List<Role> getAllRoles(){
        return service.findAll();
    }
}
