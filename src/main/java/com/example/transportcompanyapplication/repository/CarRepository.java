package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends ExtendedRepository<Car, Long> {
}
