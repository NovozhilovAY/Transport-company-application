package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.CarFinancialReport;
import com.example.transportcompanyapplication.dto.CarYearMaintCalendar;
import com.example.transportcompanyapplication.dto.CarsYearMaintCalendar;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.service.api.MaintenanceCalendarService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MaintenanceCalendarServiceImpl implements MaintenanceCalendarService {

    private final CarServiceImpl carService;

    private final FinancialReportServiceImpl financialReportService;

    public MaintenanceCalendarServiceImpl(CarServiceImpl carService, FinancialReportServiceImpl financialReportService) {
        this.carService = carService;
        this.financialReportService = financialReportService;
    }

    public NextMaintDates getNextMaintDates(Long carId) {
        Car car = carService.findById(carId);
        String to1Date = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeTo1()));
        String to2Date = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeTo2()));
        String krDate = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeKr()));
        return new NextMaintDates(to1Date, to2Date, krDate);
    }

    public CarsYearMaintCalendar getCarsYearMaintCalendar() {
        CarsYearMaintCalendar carsYearMaintCalendar = new CarsYearMaintCalendar();
        List<CarYearMaintCalendar> carYearMaintCalendars = new ArrayList<>();

        List<CarFinancialReport> carFinancialReports = financialReportService.getFinancialReport().getCarFinancialReports();
        for (CarFinancialReport carFinancialReport : carFinancialReports) {
            carYearMaintCalendars.add(getCarYearCalendarFromFinancialReport(carFinancialReport));
        }
        carsYearMaintCalendar.setCalendar(carYearMaintCalendars);
        return carsYearMaintCalendar;
    }

    private CarYearMaintCalendar getCarYearCalendarFromFinancialReport(CarFinancialReport carFinancialReport) {
        Car car = carService.findById(carFinancialReport.getId());
        CarYearMaintCalendar carYearMaintCalendar = new CarYearMaintCalendar(car);

        List<String> to1Dates = getListOfMaintDates(carFinancialReport.getNumOfTo1(),
                carFinancialReport.getAvgKilometrage(),car.getKmBeforeTo1());

        List<String> to2Dates = getListOfMaintDates(carFinancialReport.getNumOfTo2(),
                carFinancialReport.getAvgKilometrage(),car.getKmBeforeTo2());

        List<String> krDates = getListOfMaintDates(carFinancialReport.getNumOfKr(),
                carFinancialReport.getAvgKilometrage(),car.getKmBeforeKr());

        carYearMaintCalendar.setTo1Dates(to1Dates);
        carYearMaintCalendar.setTo2Dates(to2Dates);
        carYearMaintCalendar.setKrDates(krDates);
        return carYearMaintCalendar;
    }

    private List<String> getListOfMaintDates(Integer numOfActions, Double avgKilometrage, Double kmBeforeAction) {
        List<String> result = new ArrayList<>();
        if (numOfActions.equals(0)) {
            return result;
        }
        Integer numDays = getNumOfDaysBeforeMaint(avgKilometrage, kmBeforeAction);
        result.add(getDateString(numDays));
        if (numOfActions.equals(1)) {
            return result;
        }
        Integer stepDays = (365 - numDays) / (numOfActions - 1);
        for(int i = 1; i < numOfActions; i++) {
            numDays += stepDays;
            result.add(getDateString(numDays));
        }
        return result;
    }

    private String getDateString(Integer numOfDays) {
        if (numOfDays == null) {
            return null;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().plusDays(numOfDays).format(dateFormat);
    }

    private Integer getNumOfDaysBeforeMaint(Double avgKilometrage, Double kmBeforeMaint) {
        if (avgKilometrage == null) {
            return null;
        } else if (kmBeforeMaint <= 0.0) {
            return 0;
        }
        double numOfDays = kmBeforeMaint / avgKilometrage;
        return (int) numOfDays;
    }

}
