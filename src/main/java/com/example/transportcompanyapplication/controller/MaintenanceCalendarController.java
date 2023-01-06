package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.CarsYearMaintCalendar;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.service.api.MaintenanceCalendarService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/calendar")
public class MaintenanceCalendarController {

    private final MaintenanceCalendarService maintenanceCalendarService;

    public MaintenanceCalendarController(MaintenanceCalendarService maintenanceCalendarService) {
        this.maintenanceCalendarService = maintenanceCalendarService;
    }

    @GetMapping("/next-maint-dates/{carId}")
    public NextMaintDates getMaintDates(@PathVariable Long carId) {
        return maintenanceCalendarService.getNextMaintDates(carId);
    }

    @GetMapping("/maint-cars")
    public CarsYearMaintCalendar getCarsYearMaintCalendar() {
        return maintenanceCalendarService.getCarsYearMaintCalendar();
    }
}
