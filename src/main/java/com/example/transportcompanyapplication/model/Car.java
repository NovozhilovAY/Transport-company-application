package com.example.transportcompanyapplication.model;


import javax.persistence.*;
import javax.validation.constraints.*;

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
    @Min(value = 1900, message = "Enter correct year")
    @NotNull(message = "field 'year' should not be empty!")
    private Integer year;

    @Column(name = "kilometrage", nullable = false)
    @NotNull(message = "field 'kilometrage' should not be empty!")
    @PositiveOrZero(message = "kilometrage must be positive")
    private Double kilometrage;


    @Column(name = "license_plate", nullable = false)
    @NotBlank(message = "field 'licensePlate' should not be empty!")
    private String licensePlate;

    @Column(name = "maintenance_freq", nullable = false)
    @NotNull(message = "field 'maintenanceFreq' should not be empty!")
    @Positive(message = "maintenanceFreq must be positive")
    private Double maintenanceFreq;

    @Column(name = "km_before_maint", nullable = false)
    @NotNull(message = "field 'kmBeforeMaint' should not be empty!")
    private Double kmBeforeMaint;

    @Column(name = "latitude", nullable = false)
    @Min(value = -90, message = "latitude should not be less than -90")
    @Max(value = 90, message = "latitude should not be more than 90")
    @NotNull(message = "field 'latitude' should not be empty!")
    private Double latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    @Min(value = -180, message = "longitude should not be less than -180")
    @Max(value = 180, message = "longitude should not be more than 180")
    @NotNull(message = "field 'longitude' should not be empty!")
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

    public void doMaintenance(){
        this.kmBeforeMaint = this.maintenanceFreq;
    }
}