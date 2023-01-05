package com.example.transportcompanyapplication.model;


import com.example.transportcompanyapplication.dto.NextMaintDates;

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
    @Min(value = 1900, message = "")
    private Integer year;

    @Column(name = "kilometrage", nullable = false)
    @PositiveOrZero(message = "kilometrage must be positive")
    private Double kilometrage;


    @Column(name = "license_plate", nullable = false)
    @NotBlank(message = "field 'licensePlate' should not be empty!")
    private String licensePlate;

    @Column(name = "normative_to1")
    @NotNull
    @Positive(message = "normativeTo1 must be positive")
    private Double normativeTo1;

    @Column(name = "normative_to2")
    @NotNull
    @Positive(message = "normativeTo2 must be positive")
    private Double normativeTo2;

    @Column(name = "normative_kr")
    @NotNull
    @Positive(message = "normativeTo2 must be positive")
    private Double normativeKr;


    @Column(name = "fact_to1")
    private Double factTo1;

    @Column(name = "fact_to2")
    private Double factTo2;

    @Column(name = "fact_kr")
    private Double factKr;

    @Column(name = "km_before_to1", nullable = false)
    @NotNull
    private Double kmBeforeTo1;

    @Column(name = "km_before_to2", nullable = false)
    @NotNull
    private Double kmBeforeTo2;

    @Column(name = "km_before_kr", nullable = false)
    @NotNull
    private Double kmBeforeKr;

    @Column(name = "latitude", nullable = false)
    @Min(value = -90, message = "latitude should not be less than -90")
    @Max(value = 90, message = "latitude should not be more than 90")
    private Double latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    @Min(value = -180, message = "longitude should not be less than -180")
    @Max(value = 180, message = "longitude should not be more than 180")
    private Double longitude;

    @OneToOne
    @JoinColumn(name = "driver_id")
    private Driver driver;

    @Column(name = "avg_kilometrage")
    private Double avgKilometrage;

    public Car() {
    }

    public Car(Long id,
               String brand,
               String model,
               Integer year,
               Double kilometrage,
               String licensePlate,
               Double normativeTo1,
               Double normativeTo2,
               Double normativeKr,
               Double factTo1,
               Double factTo2,
               Double factKr,
               Double kmBeforeTo1,
               Double kmBeforeTo2,
               Double kmBeforeKr,
               Double latitude,
               Double longitude,
               Driver driver, Double avgKilometrage) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.year = year;
        this.kilometrage = kilometrage;
        this.licensePlate = licensePlate;
        this.normativeTo1 = normativeTo1;
        this.normativeTo2 = normativeTo2;
        this.normativeKr = normativeKr;
        this.factTo1 = factTo1;
        this.factTo2 = factTo2;
        this.factKr = factKr;
        this.kmBeforeTo1 = kmBeforeTo1;
        this.kmBeforeTo2 = kmBeforeTo2;
        this.kmBeforeKr = kmBeforeKr;
        this.latitude = latitude;
        this.longitude = longitude;
        this.driver = driver;
        this.avgKilometrage = avgKilometrage;
    }

    public Double getAvgKilometrage() {
        return avgKilometrage;
    }

    public void setAvgKilometrage(Double avgKilometrage) {
        this.avgKilometrage = avgKilometrage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public Double getNormativeTo1() {
        return normativeTo1;
    }

    public void setNormativeTo1(Double normativeTo1) {
        this.normativeTo1 = normativeTo1;
    }

    public Double getNormativeTo2() {
        return normativeTo2;
    }

    public void setNormativeTo2(Double normativeTo2) {
        this.normativeTo2 = normativeTo2;
    }

    public Double getNormativeKr() {
        return normativeKr;
    }

    public void setNormativeKr(Double normativeKr) {
        this.normativeKr = normativeKr;
    }

    public Double getFactTo1() {
        return factTo1;
    }

    public void setFactTo1(Double factTo1) {
        this.factTo1 = factTo1;
    }

    public Double getFactTo2() {
        return factTo2;
    }

    public void setFactTo2(Double factTo2) {
        this.factTo2 = factTo2;
    }

    public Double getFactKr() {
        return factKr;
    }

    public void setFactKr(Double factKr) {
        this.factKr = factKr;
    }

    public Double getKmBeforeTo1() {
        return kmBeforeTo1;
    }

    public void setKmBeforeTo1(Double kmBeforeTo1) {
        this.kmBeforeTo1 = kmBeforeTo1;
    }

    public Double getKmBeforeTo2() {
        return kmBeforeTo2;
    }

    public void setKmBeforeTo2(Double kmBeforeTo2) {
        this.kmBeforeTo2 = kmBeforeTo2;
    }

    public Double getKmBeforeKr() {
        return kmBeforeKr;
    }

    public void setKmBeforeKr(Double kmBeforeKr) {
        this.kmBeforeKr = kmBeforeKr;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public void doTo1(){
        this.kmBeforeTo1 = this.factTo1;
    }

    public void doTo2(){
        this.kmBeforeTo2 = this.factTo2;
    }
    public void doKr(){
        this.kmBeforeKr = this.factKr;
    }

    public void correctKilometrage(Double K1TO, Double K3TO, Double K1KR, Double K3KR) {
        this.factTo1 = this.normativeTo1 * K1TO * K3TO;
        this.factTo2 = this.normativeTo2 * K1TO * K3TO;
        this.factKr = this.normativeKr * K1KR * K3KR;
    }
}