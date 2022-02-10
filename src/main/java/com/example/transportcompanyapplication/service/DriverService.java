package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverService {
    private final DriverRepository repository;

    public DriverService(DriverRepository repository) {
        this.repository = repository;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findById(Long id) throws ResourceNotFoundException {
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Driver with id = " + id + " not found"));
    }

    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    public Driver delete(Long id) throws ResourceNotFoundException {
        Driver deletedDriver = findById(id);
        repository.delete(deletedDriver);
        return deletedDriver;
    }

    public Driver update(Driver driver) throws ResourceNotFoundException {
        findById(driver.getId());
        return repository.save(driver);
    }
}
