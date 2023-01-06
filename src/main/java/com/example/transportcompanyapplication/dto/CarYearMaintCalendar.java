package com.example.transportcompanyapplication.dto;

import com.example.transportcompanyapplication.model.Car;

import java.util.List;

public class CarYearMaintCalendar {

    private Long id;

    private String brand;

    private String model;

    private String licensePlate;

    private List<String> to1Dates;

    private List<String> to2Dates;

    private List<String> krDates;

    public CarYearMaintCalendar() {
    }

    public CarYearMaintCalendar(Car car) {
        this.id = car.getId();
        this.brand = car.getBrand();
        this.model = car.getModel();
        this.licensePlate = car.getLicensePlate();
    }

    public CarYearMaintCalendar(Long id, String brand,
                                String model,
                                String licensePlate,
                                List<String> to1Dates,
                                List<String> to2Dates,
                                List<String> krDates) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.licensePlate = licensePlate;
        this.to1Dates = to1Dates;
        this.to2Dates = to2Dates;
        this.krDates = krDates;
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

    public List<String> getTo1Dates() {
        return to1Dates;
    }

    public void setTo1Dates(List<String> to1Dates) {
        this.to1Dates = to1Dates;
    }

    public List<String> getTo2Dates() {
        return to2Dates;
    }

    public void setTo2Dates(List<String> to2Dates) {
        this.to2Dates = to2Dates;
    }

    public List<String> getKrDates() {
        return krDates;
    }

    public void setKrDates(List<String> krDates) {
        this.krDates = krDates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
