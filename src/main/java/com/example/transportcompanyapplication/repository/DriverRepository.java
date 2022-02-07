package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.Driver;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {

}
