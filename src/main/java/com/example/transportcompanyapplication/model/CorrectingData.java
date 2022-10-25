package com.example.transportcompanyapplication.model;

import com.example.transportcompanyapplication.correcting.model.CityType;
import com.example.transportcompanyapplication.correcting.model.ClimateType;
import com.example.transportcompanyapplication.correcting.model.ReliefType;
import com.example.transportcompanyapplication.correcting.model.RoadType;

import javax.persistence.*;

@Entity
@Table(name = "correcting_data")
public class CorrectingData {
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "city_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private CityType cityType;

    @Column(name = "climate_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ClimateType climateType;

    @Column(name = "relief_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private ReliefType reliefType;

    @Column(name = "road_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoadType roadType;

    public CorrectingData() {
    }

    public CorrectingData(Long id, CityType cityType, ClimateType climateType, ReliefType reliefType, RoadType roadType) {
        this.id = id;
        this.cityType = cityType;
        this.climateType = climateType;
        this.reliefType = reliefType;
        this.roadType = roadType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CityType getCityType() {
        return cityType;
    }

    public void setCityType(CityType cityType) {
        this.cityType = cityType;
    }

    public ClimateType getClimateType() {
        return climateType;
    }

    public void setClimateType(ClimateType climateType) {
        this.climateType = climateType;
    }

    public ReliefType getReliefType() {
        return reliefType;
    }

    public void setReliefType(ReliefType reliefType) {
        this.reliefType = reliefType;
    }

    public RoadType getRoadType() {
        return roadType;
    }

    public void setRoadType(RoadType roadType) {
        this.roadType = roadType;
    }
}
