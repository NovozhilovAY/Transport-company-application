package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drivers")
public class DriverController {

    final private DriverRepository repository;

    public DriverController(DriverRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Driver with id = " + id + " not found"));
    }

    @PostMapping
    public Driver createDriver(@RequestBody Driver driver){
        return repository.save(driver);
    }

    @DeleteMapping("/{id}")
    public Driver deleteDriver(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Driver driver = repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Driver with id = " + id + " not found")
        );
        repository.delete(driver);
        return driver;
    }

    @PutMapping
    public Driver updateDriver(@RequestBody Driver driver) throws ResourceNotFoundException {
        repository.findById(driver.getId()).orElseThrow(
                ()-> new ResourceNotFoundException("Driver with id = " + driver.getId() + " not found")
        );
        return repository.save(driver);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Response> handleException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new Response(e.getMessage()), HttpStatus.NOT_FOUND);
    }

}
