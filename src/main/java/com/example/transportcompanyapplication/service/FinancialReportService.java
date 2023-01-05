package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.repository.FinancialReportRepository;
import org.springframework.stereotype.Service;

@Service
public class FinancialReportService {
    private final FinancialReportRepository repository;

    public FinancialReportService(FinancialReportRepository repository) {
        this.repository = repository;
    }

    public FinancialReportData getFinancialReportData() {
        return repository.findById(1L).orElseThrow(
                () -> new ResourceNotFoundException("Не удалось получить доступ к данным финансового отчета!")
        );
    }

    public FinancialReportData updateReportData(FinancialReportData financialReportData) {
        repository.update(financialReportData);
        return getFinancialReportData();
    }
}
