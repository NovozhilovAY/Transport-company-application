package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectingRepository extends ExtendedRepository<CorrectingData, Long> {
}
