package com.example.transportcompanyapplication.dto;


import com.example.transportcompanyapplication.model.Car;

public class CarFinancialReport {

    private String brand;

    private String model;

    private String licensePlate;

    private Double avgKilometrage;

    private Double yearKilometrage;

    private Integer numOfTo1;

    private Integer numOfTo2;

    private Integer numOfKr;

    private Integer costOfTo1;

    private Integer costOfTo2;

    private Integer costOfKr;

    public CarFinancialReport() {
    }

    public CarFinancialReport(String brand,
                              String model,
                              String licensePlate,
                              Double avgKilometrage,
                              Double yearKilometrage,
                              Integer numOfTo1,
                              Integer numOfTo2,
                              Integer numOfKr,
                              Integer costOfTo1,
                              Integer costOfTo2,
                              Integer costOfKr) {
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.avgKilometrage = avgKilometrage;
        this.yearKilometrage = yearKilometrage;
        this.numOfTo1 = numOfTo1;
        this.numOfTo2 = numOfTo2;
        this.numOfKr = numOfKr;
        this.costOfTo1 = costOfTo1;
        this.costOfTo2 = costOfTo2;
        this.costOfKr = costOfKr;
    }

    public CarFinancialReport(Car car) {
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.avgKilometrage = car.getAvgKilometrage();
        this.licensePlate = car.getLicensePlate();
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getAvgKilometrage() {
        return avgKilometrage;
    }

    public void setAvgKilometrage(Double avgKilometrage) {
        this.avgKilometrage = avgKilometrage;
    }

    public Double getYearKilometrage() {
        return yearKilometrage;
    }

    public void setYearKilometrage(Double yearKilometrage) {
        this.yearKilometrage = yearKilometrage;
    }

    public Integer getNumOfTo1() {
        return numOfTo1;
    }

    public void setNumOfTo1(Integer numOfTo1) {
        this.numOfTo1 = numOfTo1;
    }

    public Integer getNumOfTo2() {
        return numOfTo2;
    }

    public void setNumOfTo2(Integer numOfTo2) {
        this.numOfTo2 = numOfTo2;
    }

    public Integer getNumOfKr() {
        return numOfKr;
    }

    public void setNumOfKr(Integer numOfKr) {
        this.numOfKr = numOfKr;
    }

    public Integer getCostOfTo1() {
        return costOfTo1;
    }

    public void setCostOfTo1(Integer costOfTo1) {
        this.costOfTo1 = costOfTo1;
    }

    public Integer getCostOfTo2() {
        return costOfTo2;
    }

    public void setCostOfTo2(Integer costOfTo2) {
        this.costOfTo2 = costOfTo2;
    }

    public Integer getCostOfKr() {
        return costOfKr;
    }

    public void setCostOfKr(Integer costOfKr) {
        this.costOfKr = costOfKr;
    }
}
