package com.example.transportcompanyapplication.dto;

import java.util.List;

public class FinancialReport {

    private List<CarFinancialReport> carFinancialReports;

    private Double totalKilometrage;

    private Integer totalNumOfTo1;

    private Integer totalNumOfTo2;

    private Integer totalNumOfKr;

    private Integer totalCostTo1;

    private Integer totalCostTo2;

    private Integer totalCostKr;

    public FinancialReport() {
    }

    public FinancialReport(List<CarFinancialReport> carFinancialReports,
                           Double totalKilometrage,
                           Integer totalNumOfTo1,
                           Integer totalNumOfTo2,
                           Integer totalNumOfKr,
                           Integer totalCostTo1,
                           Integer totalCostTo2,
                           Integer totalCostKr) {
        this.carFinancialReports = carFinancialReports;
        this.totalKilometrage = totalKilometrage;
        this.totalNumOfTo1 = totalNumOfTo1;
        this.totalNumOfTo2 = totalNumOfTo2;
        this.totalNumOfKr = totalNumOfKr;
        this.totalCostTo1 = totalCostTo1;
        this.totalCostTo2 = totalCostTo2;
        this.totalCostKr = totalCostKr;
    }

    public List<CarFinancialReport> getCarFinancialReports() {
        return carFinancialReports;
    }

    public void setCarFinancialReports(List<CarFinancialReport> carFinancialReports) {
        this.carFinancialReports = carFinancialReports;
    }

    public Double getTotalKilometrage() {
        return totalKilometrage;
    }

    public void setTotalKilometrage(Double totalKilometrage) {
        this.totalKilometrage = totalKilometrage;
    }

    public Integer getTotalNumOfTo1() {
        return totalNumOfTo1;
    }

    public void setTotalNumOfTo1(Integer totalNumOfTo1) {
        this.totalNumOfTo1 = totalNumOfTo1;
    }

    public Integer getTotalNumOfTo2() {
        return totalNumOfTo2;
    }

    public void setTotalNumOfTo2(Integer totalNumOfTo2) {
        this.totalNumOfTo2 = totalNumOfTo2;
    }

    public Integer getTotalNumOfKr() {
        return totalNumOfKr;
    }

    public void setTotalNumOfKr(Integer totalNumOfKr) {
        this.totalNumOfKr = totalNumOfKr;
    }

    public Integer getTotalCostTo1() {
        return totalCostTo1;
    }

    public void setTotalCostTo1(Integer totalCostTo1) {
        this.totalCostTo1 = totalCostTo1;
    }

    public Integer getTotalCostTo2() {
        return totalCostTo2;
    }

    public void setTotalCostTo2(Integer totalCostTo2) {
        this.totalCostTo2 = totalCostTo2;
    }

    public Integer getTotalCostKr() {
        return totalCostKr;
    }

    public void setTotalCostKr(Integer totalCostKr) {
        this.totalCostKr = totalCostKr;
    }
}
