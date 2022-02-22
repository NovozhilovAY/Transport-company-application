package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface DriverRepository extends ExtendedRepository<Driver, Long> {
}
