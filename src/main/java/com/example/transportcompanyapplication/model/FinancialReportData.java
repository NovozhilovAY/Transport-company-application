package com.example.transportcompanyapplication.model;

import com.example.transportcompanyapplication.correcting.model.CityType;

import javax.persistence.*;

@Entity
@Table(name = "financial_report_data")
public class FinancialReportData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "num_working_days")
    private Integer numWorkingDays;

    @Column(name = "to1_cost")
    private Integer to1Cost;

    @Column(name = "to2_cost")
    private Integer to2Cost;

    @Column(name = "kr_cost")
    private Integer krCost;

    public FinancialReportData() {
    }

    public FinancialReportData(Long id, Integer numWorkingDays, Integer to1Cost, Integer to2Cost, Integer krCost) {
        this.id = id;
        this.numWorkingDays = numWorkingDays;
        this.to1Cost = to1Cost;
        this.to2Cost = to2Cost;
        this.krCost = krCost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumWorkingDays() {
        return numWorkingDays;
    }

    public void setNumWorkingDays(Integer numWorkingDays) {
        this.numWorkingDays = numWorkingDays;
    }

    public Integer getTo1Cost() {
        return to1Cost;
    }

    public void setTo1Cost(Integer to1Cost) {
        this.to1Cost = to1Cost;
    }

    public Integer getTo2Cost() {
        return to2Cost;
    }

    public void setTo2Cost(Integer to2Cost) {
        this.to2Cost = to2Cost;
    }

    public Integer getKrCost() {
        return krCost;
    }

    public void setKrCost(Integer krCost) {
        this.krCost = krCost;
    }
}
