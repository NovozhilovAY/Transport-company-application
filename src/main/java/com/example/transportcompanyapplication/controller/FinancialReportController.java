package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.service.api.FinancialReportService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/financial-report")
public class FinancialReportController {

    private final FinancialReportService service;

    public FinancialReportController(FinancialReportService service) {
        this.service = service;
    }

    @GetMapping("/data")
    public FinancialReportData getFinancialReportData() {
        return service.getFinancialReportData();
    }

    @PutMapping("/data")
    public FinancialReportData updateFinancialReportData(@RequestBody FinancialReportData financialReportData) {
        return service.updateReportData(financialReportData);
    }

    @GetMapping
    public FinancialReport getFinancialReport() {
        return service.getFinancialReport();
    }

}
