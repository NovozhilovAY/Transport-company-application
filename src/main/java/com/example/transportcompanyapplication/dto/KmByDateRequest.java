package com.example.transportcompanyapplication.dto;

import javax.validation.constraints.Past;
import javax.validation.constraints.Positive;
import java.sql.Date;

public class KmByDateRequest {
    @Positive
    private Long carId;
    @Past(message = "date should be correct")
    private Date date;

    public KmByDateRequest(Long carId, Date date) {
        this.carId = carId;
        this.date = date;
    }

    public KmByDateRequest() {
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
