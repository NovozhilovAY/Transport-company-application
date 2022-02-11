package com.example.transportcompanyapplication.model;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "history")
public class HistoryRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "car_id", nullable = false)
    private Long carId;

    @Column(name = "h_date", nullable = false)
    private Date date;

    @Column(name = "kilometrage", nullable = false)
    private Double kilometrage;

    public Double getKilometrage() {
        return kilometrage;
    }

    public void setKilometrage(Double kilometrage) {
        this.kilometrage = kilometrage;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getCarId() {
        return carId;
    }

    public void setCarId(Long carId) {
        this.carId = carId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}