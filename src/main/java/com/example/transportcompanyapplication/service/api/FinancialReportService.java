package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.model.FinancialReportData;

public interface FinancialReportService {

    FinancialReportData getFinancialReportData();

    FinancialReportData updateReportData(FinancialReportData financialReportData);

    FinancialReport getFinancialReport();
}
