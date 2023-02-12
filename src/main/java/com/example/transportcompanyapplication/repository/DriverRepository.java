package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DriverRepository extends ExtendedRepository<Driver, Long> {
    @Query(value = "SELECT " +
            "d.id, d.last_name, d.first_name, d.middle_name, d.driving_license" +
            " FROM cars right join drivers d on d.id = cars.driver_id where driver_id IS NULL"
            , nativeQuery = true)
    List<Driver> getFreeDrivers();
}
