package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.CorrectingData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CorrectingRepository extends JpaRepository<CorrectingData, Long> {
}
