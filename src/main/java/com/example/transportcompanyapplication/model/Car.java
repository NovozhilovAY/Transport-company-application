package com.example.transportcompanyapplication.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

@Entity
@Table(name = "cars", indexes = {
        @Index(name = "cars_license_plate_key", columnList = "license_plate", unique = true),
        @Index(name = "cars_driver_id_key", columnList = "driver_id", unique = true)
})
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;


    @Column(name = "brand", nullable = false)
    @NotBlank(message = "field 'brand' should not be empty!")
    private String brand;


    @Column(name = "model", nullable = false)
    @NotBlank(message = "field 'model' should not be empty!")
    private String model;

    @Column(name = "year", nullable = false)
    private Integer year;

    @Column(name = "kilometrage", nullable = false)
    @PositiveOrZero(message = "kilometrage must be positive")
    private Double kilometrage;


    @Column(name = "license_plate", nullable = false)
    private String licensePlate;

    @Column(name = "maintenance_freq", nullable = false)
    private Double maintenanceFreq;

    @Column(name = "km_before_maint", nullable = false)
    private Double kmBeforeMaint;

    @Column(name = "latitude", nullable = false)
    private Double latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    public Car(Long id,
               String brand,
               String model,
               Integer year,
               Double kilometrage,
               String licensePlate,
               Double maintenanceFreq,
               Double kmBeforeMaint,
               Double latitude,
               Double longitude,
               Driver driver) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometrage = kilometrage;
        this.licensePlate = licensePlate;
        this.maintenanceFreq = maintenanceFreq;
        this.kmBeforeMaint = kmBeforeMaint;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driver = driver;
    }

    public Car() {
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getKmBeforeMaint() {
        return kmBeforeMaint;
    }

    public void setKmBeforeMaint(Double kmBeforeMaint) {
        this.kmBeforeMaint = kmBeforeMaint;
    }

    public Double getMaintenanceFreq() {
        return maintenanceFreq;
    }

    public void setMaintenanceFreq(Double maintenanceFreq) {
        this.maintenanceFreq = maintenanceFreq;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}