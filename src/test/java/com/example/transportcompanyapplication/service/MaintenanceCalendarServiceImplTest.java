package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.CarFinancialReport;
import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.service.api.FinancialReportService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MaintenanceCalendarServiceImpl.class})
@ExtendWith(SpringExtension.class)
class MaintenanceCalendarServiceImplTest {
    @MockBean
    private CarServiceImpl carServiceImpl;

    @MockBean
    private FinancialReportService financialReportService;

    @Autowired
    private MaintenanceCalendarServiceImpl maintenanceCalendarServiceImpl;

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getNextMaintDates(Long)}
     */
    @Test
    void testGetNextMaintDates() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");

        Car car = new Car();
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        maintenanceCalendarServiceImpl.getNextMaintDates(123L);
        verify(carServiceImpl).findById((Long) any());
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getNextMaintDates(Long)}
     */
    @Test
    void testGetNextMaintDates2() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getAvgKilometrage()).thenReturn(-0.5d);
        when(car.getKmBeforeKr()).thenReturn(10.0d);
        when(car.getKmBeforeTo1()).thenReturn(10.0d);
        when(car.getKmBeforeTo2()).thenReturn(10.0d);
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDriver((Driver) any());
        doNothing().when(car).setFactKr((Double) any());
        doNothing().when(car).setFactTo1((Double) any());
        doNothing().when(car).setFactTo2((Double) any());
        doNothing().when(car).setId((Long) any());
        doNothing().when(car).setKilometrage((Double) any());
        doNothing().when(car).setKmBeforeKr((Double) any());
        doNothing().when(car).setKmBeforeTo1((Double) any());
        doNothing().when(car).setKmBeforeTo2((Double) any());
        doNothing().when(car).setLatitude((Double) any());
        doNothing().when(car).setLicensePlate((String) any());
        doNothing().when(car).setLongitude((Double) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setNormativeKr((Double) any());
        doNothing().when(car).setNormativeTo1((Double) any());
        doNothing().when(car).setNormativeTo2((Double) any());
        doNothing().when(car).setYear((Integer) any());
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        NextMaintDates actualNextMaintDates = maintenanceCalendarServiceImpl.getNextMaintDates(123L);
        assertEquals("-", actualNextMaintDates.getNextKrDate());
        assertEquals("-", actualNextMaintDates.getNextTo2Date());
        assertEquals("-", actualNextMaintDates.getNextTo1Date());
        verify(carServiceImpl).findById((Long) any());
        verify(car, atLeast(1)).getAvgKilometrage();
        verify(car).getKmBeforeKr();
        verify(car).getKmBeforeTo1();
        verify(car).getKmBeforeTo2();
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDriver((Driver) any());
        verify(car).setFactKr((Double) any());
        verify(car).setFactTo1((Double) any());
        verify(car).setFactTo2((Double) any());
        verify(car).setId((Long) any());
        verify(car).setKilometrage((Double) any());
        verify(car).setKmBeforeKr((Double) any());
        verify(car).setKmBeforeTo1((Double) any());
        verify(car).setKmBeforeTo2((Double) any());
        verify(car).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar2() {
        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(new ArrayList<>());
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertTrue(maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().isEmpty());
        verify(financialReportService).getFinancialReport();
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar6() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");

        Car car = new Car();
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);

        ArrayList<CarFinancialReport> carFinancialReportList = new ArrayList<>();
        carFinancialReportList
                .add(new CarFinancialReport("Brand", "Model", "License Plate", 10.0d, 10.0d, 10, 10, 10, 1, 1, 1, 123L));

        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(carFinancialReportList);
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertEquals(1, maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().size());
        verify(carServiceImpl).findById((Long) any());
        verify(financialReportService).getFinancialReport();
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar7() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getKmBeforeKr()).thenReturn(0.5d);
        when(car.getKmBeforeTo1()).thenReturn(10.0d);
        when(car.getKmBeforeTo2()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
        when(car.getBrand()).thenReturn("Brand");
        when(car.getLicensePlate()).thenReturn("License Plate");
        when(car.getModel()).thenReturn("Model");
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDriver((Driver) any());
        doNothing().when(car).setFactKr((Double) any());
        doNothing().when(car).setFactTo1((Double) any());
        doNothing().when(car).setFactTo2((Double) any());
        doNothing().when(car).setId((Long) any());
        doNothing().when(car).setKilometrage((Double) any());
        doNothing().when(car).setKmBeforeKr((Double) any());
        doNothing().when(car).setKmBeforeTo1((Double) any());
        doNothing().when(car).setKmBeforeTo2((Double) any());
        doNothing().when(car).setLatitude((Double) any());
        doNothing().when(car).setLicensePlate((String) any());
        doNothing().when(car).setLongitude((Double) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setNormativeKr((Double) any());
        doNothing().when(car).setNormativeTo1((Double) any());
        doNothing().when(car).setNormativeTo2((Double) any());
        doNothing().when(car).setYear((Integer) any());
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        CarFinancialReport carFinancialReport = mock(CarFinancialReport.class);
        when(carFinancialReport.getAvgKilometrage()).thenReturn(10.0d);
        when(carFinancialReport.getNumOfKr()).thenReturn(10);
        when(carFinancialReport.getNumOfTo1()).thenReturn(10);
        when(carFinancialReport.getNumOfTo2()).thenReturn(10);
        when(carFinancialReport.getId()).thenReturn(123L);

        ArrayList<CarFinancialReport> carFinancialReportList = new ArrayList<>();
        carFinancialReportList.add(carFinancialReport);

        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(carFinancialReportList);
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertEquals(1, maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().size());
        verify(carServiceImpl).findById((Long) any());
        verify(car).getKmBeforeKr();
        verify(car).getKmBeforeTo1();
        verify(car).getKmBeforeTo2();
        verify(car).getId();
        verify(car).getBrand();
        verify(car).getLicensePlate();
        verify(car).getModel();
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDriver((Driver) any());
        verify(car).setFactKr((Double) any());
        verify(car).setFactTo1((Double) any());
        verify(car).setFactTo2((Double) any());
        verify(car).setId((Long) any());
        verify(car).setKilometrage((Double) any());
        verify(car).setKmBeforeKr((Double) any());
        verify(car).setKmBeforeTo1((Double) any());
        verify(car).setKmBeforeTo2((Double) any());
        verify(car).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
        verify(financialReportService).getFinancialReport();
        verify(carFinancialReport, atLeast(1)).getAvgKilometrage();
        verify(carFinancialReport).getNumOfKr();
        verify(carFinancialReport).getNumOfTo1();
        verify(carFinancialReport).getNumOfTo2();
        verify(carFinancialReport).getId();
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar8() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getKmBeforeKr()).thenReturn(10.0d);
        when(car.getKmBeforeTo1()).thenReturn(10.0d);
        when(car.getKmBeforeTo2()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
        when(car.getBrand()).thenReturn("Brand");
        when(car.getLicensePlate()).thenReturn("License Plate");
        when(car.getModel()).thenReturn("Model");
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDriver((Driver) any());
        doNothing().when(car).setFactKr((Double) any());
        doNothing().when(car).setFactTo1((Double) any());
        doNothing().when(car).setFactTo2((Double) any());
        doNothing().when(car).setId((Long) any());
        doNothing().when(car).setKilometrage((Double) any());
        doNothing().when(car).setKmBeforeKr((Double) any());
        doNothing().when(car).setKmBeforeTo1((Double) any());
        doNothing().when(car).setKmBeforeTo2((Double) any());
        doNothing().when(car).setLatitude((Double) any());
        doNothing().when(car).setLicensePlate((String) any());
        doNothing().when(car).setLongitude((Double) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setNormativeKr((Double) any());
        doNothing().when(car).setNormativeTo1((Double) any());
        doNothing().when(car).setNormativeTo2((Double) any());
        doNothing().when(car).setYear((Integer) any());
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        CarFinancialReport carFinancialReport = mock(CarFinancialReport.class);
        when(carFinancialReport.getAvgKilometrage()).thenReturn(-0.5d);
        when(carFinancialReport.getNumOfKr()).thenReturn(10);
        when(carFinancialReport.getNumOfTo1()).thenReturn(10);
        when(carFinancialReport.getNumOfTo2()).thenReturn(10);
        when(carFinancialReport.getId()).thenReturn(123L);

        ArrayList<CarFinancialReport> carFinancialReportList = new ArrayList<>();
        carFinancialReportList.add(carFinancialReport);

        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(carFinancialReportList);
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertEquals(1, maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().size());
        verify(carServiceImpl).findById((Long) any());
        verify(car).getKmBeforeKr();
        verify(car).getKmBeforeTo1();
        verify(car).getKmBeforeTo2();
        verify(car).getId();
        verify(car).getBrand();
        verify(car).getLicensePlate();
        verify(car).getModel();
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDriver((Driver) any());
        verify(car).setFactKr((Double) any());
        verify(car).setFactTo1((Double) any());
        verify(car).setFactTo2((Double) any());
        verify(car).setId((Long) any());
        verify(car).setKilometrage((Double) any());
        verify(car).setKmBeforeKr((Double) any());
        verify(car).setKmBeforeTo1((Double) any());
        verify(car).setKmBeforeTo2((Double) any());
        verify(car).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
        verify(financialReportService).getFinancialReport();
        verify(carFinancialReport, atLeast(1)).getAvgKilometrage();
        verify(carFinancialReport).getNumOfKr();
        verify(carFinancialReport).getNumOfTo1();
        verify(carFinancialReport).getNumOfTo2();
        verify(carFinancialReport).getId();
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar9() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getKmBeforeKr()).thenReturn(10.0d);
        when(car.getKmBeforeTo1()).thenReturn(10.0d);
        when(car.getKmBeforeTo2()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
        when(car.getBrand()).thenReturn("Brand");
        when(car.getLicensePlate()).thenReturn("License Plate");
        when(car.getModel()).thenReturn("Model");
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDriver((Driver) any());
        doNothing().when(car).setFactKr((Double) any());
        doNothing().when(car).setFactTo1((Double) any());
        doNothing().when(car).setFactTo2((Double) any());
        doNothing().when(car).setId((Long) any());
        doNothing().when(car).setKilometrage((Double) any());
        doNothing().when(car).setKmBeforeKr((Double) any());
        doNothing().when(car).setKmBeforeTo1((Double) any());
        doNothing().when(car).setKmBeforeTo2((Double) any());
        doNothing().when(car).setLatitude((Double) any());
        doNothing().when(car).setLicensePlate((String) any());
        doNothing().when(car).setLongitude((Double) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setNormativeKr((Double) any());
        doNothing().when(car).setNormativeTo1((Double) any());
        doNothing().when(car).setNormativeTo2((Double) any());
        doNothing().when(car).setYear((Integer) any());
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        CarFinancialReport carFinancialReport = mock(CarFinancialReport.class);
        when(carFinancialReport.getAvgKilometrage()).thenReturn(10.0d);
        when(carFinancialReport.getNumOfKr()).thenReturn(1);
        when(carFinancialReport.getNumOfTo1()).thenReturn(10);
        when(carFinancialReport.getNumOfTo2()).thenReturn(10);
        when(carFinancialReport.getId()).thenReturn(123L);

        ArrayList<CarFinancialReport> carFinancialReportList = new ArrayList<>();
        carFinancialReportList.add(carFinancialReport);

        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(carFinancialReportList);
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertEquals(1, maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().size());
        verify(carServiceImpl).findById((Long) any());
        verify(car).getKmBeforeKr();
        verify(car).getKmBeforeTo1();
        verify(car).getKmBeforeTo2();
        verify(car).getId();
        verify(car).getBrand();
        verify(car).getLicensePlate();
        verify(car).getModel();
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDriver((Driver) any());
        verify(car).setFactKr((Double) any());
        verify(car).setFactTo1((Double) any());
        verify(car).setFactTo2((Double) any());
        verify(car).setId((Long) any());
        verify(car).setKilometrage((Double) any());
        verify(car).setKmBeforeKr((Double) any());
        verify(car).setKmBeforeTo1((Double) any());
        verify(car).setKmBeforeTo2((Double) any());
        verify(car).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
        verify(financialReportService).getFinancialReport();
        verify(carFinancialReport, atLeast(1)).getAvgKilometrage();
        verify(carFinancialReport).getNumOfKr();
        verify(carFinancialReport).getNumOfTo1();
        verify(carFinancialReport).getNumOfTo2();
        verify(carFinancialReport).getId();
    }

    /**
     * Method under test: {@link MaintenanceCalendarServiceImpl#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar10() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getKmBeforeKr()).thenReturn(10.0d);
        when(car.getKmBeforeTo1()).thenReturn(10.0d);
        when(car.getKmBeforeTo2()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
        when(car.getBrand()).thenReturn("Brand");
        when(car.getLicensePlate()).thenReturn("License Plate");
        when(car.getModel()).thenReturn("Model");
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDriver((Driver) any());
        doNothing().when(car).setFactKr((Double) any());
        doNothing().when(car).setFactTo1((Double) any());
        doNothing().when(car).setFactTo2((Double) any());
        doNothing().when(car).setId((Long) any());
        doNothing().when(car).setKilometrage((Double) any());
        doNothing().when(car).setKmBeforeKr((Double) any());
        doNothing().when(car).setKmBeforeTo1((Double) any());
        doNothing().when(car).setKmBeforeTo2((Double) any());
        doNothing().when(car).setLatitude((Double) any());
        doNothing().when(car).setLicensePlate((String) any());
        doNothing().when(car).setLongitude((Double) any());
        doNothing().when(car).setModel((String) any());
        doNothing().when(car).setNormativeKr((Double) any());
        doNothing().when(car).setNormativeTo1((Double) any());
        doNothing().when(car).setNormativeTo2((Double) any());
        doNothing().when(car).setYear((Integer) any());
        car.setAvgKilometrage(10.0d);
        car.setBrand("Brand");
        car.setDriver(driver);
        car.setFactKr(10.0d);
        car.setFactTo1(10.0d);
        car.setFactTo2(10.0d);
        car.setId(123L);
        car.setKilometrage(10.0d);
        car.setKmBeforeKr(10.0d);
        car.setKmBeforeTo1(10.0d);
        car.setKmBeforeTo2(10.0d);
        car.setLatitude(10.0d);
        car.setLicensePlate("License Plate");
        car.setLongitude(10.0d);
        car.setModel("Model");
        car.setNormativeKr(10.0d);
        car.setNormativeTo1(10.0d);
        car.setNormativeTo2(10.0d);
        car.setYear(1);
        when(carServiceImpl.findById((Long) any())).thenReturn(car);
        CarFinancialReport carFinancialReport = mock(CarFinancialReport.class);
        when(carFinancialReport.getAvgKilometrage()).thenReturn(10.0d);
        when(carFinancialReport.getNumOfKr()).thenReturn(0);
        when(carFinancialReport.getNumOfTo1()).thenReturn(10);
        when(carFinancialReport.getNumOfTo2()).thenReturn(10);
        when(carFinancialReport.getId()).thenReturn(123L);

        ArrayList<CarFinancialReport> carFinancialReportList = new ArrayList<>();
        carFinancialReportList.add(carFinancialReport);

        FinancialReport financialReport = new FinancialReport();
        financialReport.setCarFinancialReports(carFinancialReportList);
        when(financialReportService.getFinancialReport()).thenReturn(financialReport);
        assertEquals(1, maintenanceCalendarServiceImpl.getCarsYearMaintCalendar().getCalendar().size());
        verify(carServiceImpl).findById((Long) any());
        verify(car).getKmBeforeKr();
        verify(car).getKmBeforeTo1();
        verify(car).getKmBeforeTo2();
        verify(car).getId();
        verify(car).getBrand();
        verify(car).getLicensePlate();
        verify(car).getModel();
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDriver((Driver) any());
        verify(car).setFactKr((Double) any());
        verify(car).setFactTo1((Double) any());
        verify(car).setFactTo2((Double) any());
        verify(car).setId((Long) any());
        verify(car).setKilometrage((Double) any());
        verify(car).setKmBeforeKr((Double) any());
        verify(car).setKmBeforeTo1((Double) any());
        verify(car).setKmBeforeTo2((Double) any());
        verify(car).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
        verify(financialReportService).getFinancialReport();
        verify(carFinancialReport, atLeast(1)).getAvgKilometrage();
        verify(carFinancialReport).getNumOfKr();
        verify(carFinancialReport).getNumOfTo1();
        verify(carFinancialReport).getNumOfTo2();
        verify(carFinancialReport).getId();
    }
}

