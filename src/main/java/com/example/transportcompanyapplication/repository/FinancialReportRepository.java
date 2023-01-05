package com.example.transportcompanyapplication.repository;

import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.repository.extended.ExtendedRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialReportRepository extends ExtendedRepository<FinancialReportData, Long> {
}
