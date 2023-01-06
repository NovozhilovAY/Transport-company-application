package com.example.transportcompanyapplication.dto;

import java.util.List;

public class CarsYearMaintCalendar {
    private List<CarYearMaintCalendar> calendar;

    public CarsYearMaintCalendar() {
    }

    public CarsYearMaintCalendar(List<CarYearMaintCalendar> calendar) {
        this.calendar = calendar;
    }

    public List<CarYearMaintCalendar> getCalendar() {
        return calendar;
    }

    public void setCalendar(List<CarYearMaintCalendar> calendar) {
        this.calendar = calendar;
    }
}
