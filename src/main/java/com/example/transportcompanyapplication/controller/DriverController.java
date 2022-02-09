package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.service.DriverService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    final private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return service.findById(id);
    }

    @PostMapping
    public Driver createDriver(@RequestBody @Valid Driver driver){
        return service.save(driver);
    }

    @DeleteMapping("/{id}")
    public Driver deleteDriver(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return service.delete(id);
    }

    @PutMapping
    public Driver updateDriver(@RequestBody @Valid Driver driver) throws ResourceNotFoundException {
        return service.update(driver);
    }
}
