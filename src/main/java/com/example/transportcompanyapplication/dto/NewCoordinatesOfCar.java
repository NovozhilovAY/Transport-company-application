package com.example.transportcompanyapplication.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Positive;

public class NewCoordinatesOfCar {
    @Positive
    private Long carId;

    @Min(value = -90, message = "latitude should not be less than -90")
    @Max(value = 90, message = "latitude should not be more than 90")
    private Double latitude;

    @Min(value = -180, message = "longitude should not be less than -180")
    @Max(value = 180, message = "longitude should not be more than 180")
    private Double longitude;

    public NewCoordinatesOfCar() {
    }

    public NewCoordinatesOfCar(Long carId, Double latitude, Double longitude) {
        this.carId = carId;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
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
}
