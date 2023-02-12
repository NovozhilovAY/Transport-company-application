package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.model.Driver;

import java.util.List;

public interface DriverService extends CommonService<Driver, Long> {
    List<Driver> getFreeDrivers();
}
