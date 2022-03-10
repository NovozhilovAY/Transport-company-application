package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class DriverService extends AbstractService<Driver, Long>{
    public DriverService(DriverRepository repository, PatchMapper<Driver> mapper) {
        super(repository,mapper);
    }
    public List<Driver> getFreeDrivers(){
        return ((DriverRepository)repository).getFreeDrivers();
    }
}
