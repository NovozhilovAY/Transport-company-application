package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DriverService {
    private final DriverRepository repository;
    private final PatchMapper<Driver> mapper;

    public DriverService(DriverRepository repository, PatchMapper<Driver> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Driver> findAll() {
        return repository.findAll();
    }

    public Driver findById(Long id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Driver with id = " + id + " not found"));
    }

    public Driver save(Driver driver) {
        return repository.save(driver);
    }

    public void delete(Long id){
        Driver deletedDriver = findById(id);
        repository.delete(deletedDriver);
    }

    public Driver update(Driver driver){
        findById(driver.getId());
        return repository.save(driver);
    }

    public Driver partialUpdate(Long id,Driver driver){
        Driver updatedDriver = findById(id);
        mapper.update(driver,updatedDriver);
        return repository.saveAndFlush(updatedDriver);
    }
}
