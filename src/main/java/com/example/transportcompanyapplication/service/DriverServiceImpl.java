package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import com.example.transportcompanyapplication.service.api.DriverService;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DriverServiceImpl extends CommonServiceImpl<Driver, Long> implements DriverService {

    public DriverServiceImpl(DriverRepository repository, PatchMapper<Driver> mapper) {
        super(repository,mapper);
    }

    @Override
    public List<Driver> getFreeDrivers(){
        return ((DriverRepository)repository).getFreeDrivers();
    }
}
