package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.CarsYearMaintCalendar;
import com.example.transportcompanyapplication.dto.NextMaintDates;

public interface MaintenanceCalendarService {

    NextMaintDates getNextMaintDates(Long carId);

    CarsYearMaintCalendar getCarsYearMaintCalendar();
}
