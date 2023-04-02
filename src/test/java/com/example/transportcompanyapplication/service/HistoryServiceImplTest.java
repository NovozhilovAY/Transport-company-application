package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.DateInterval;
import com.example.transportcompanyapplication.dto.HistoryDatesRequest;
import com.example.transportcompanyapplication.dto.HistoryIntervalsRequest;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;

import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {HistoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class HistoryServiceImplTest {
    @MockBean
    private CarRepository carRepository;

    @MockBean
    private HistoryRepository historyRepository;

    @Autowired
    private HistoryServiceImpl historyServiceImpl;

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDate(HistoryDatesRequest)}
     */
    @Test
    void testGetKilometrageByDate2() {
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> historyServiceImpl.getKilometrageByDate(new HistoryDatesRequest()));
        verify(carRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDate(HistoryDatesRequest)}
     */
    @Test
    void testGetKilometrageByDate3() {
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
        Optional<Car> ofResult = Optional.of(car);
        when(carRepository.findById((Long) any())).thenReturn(ofResult);

        HistoryDatesRequest historyDatesRequest = new HistoryDatesRequest();
        historyDatesRequest.setDates(new ArrayList<>());
        assertTrue(historyServiceImpl.getKilometrageByDate(historyDatesRequest).isEmpty());
        verify(carRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDate(HistoryDatesRequest)}
     */
    @Test
    void testGetKilometrageByDate5() {
        when(historyRepository.getKmByDate((Long) any(), (Date) any())).thenReturn(10.0d);

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
        Optional<Car> ofResult = Optional.of(car);
        when(carRepository.findById((Long) any())).thenReturn(ofResult);

        ArrayList<Date> dateList = new ArrayList<>();
        dateList.add(mock(Date.class));

        HistoryDatesRequest historyDatesRequest = new HistoryDatesRequest();
        historyDatesRequest.setDates(dateList);
        List<Double> actualKilometrageByDate = historyServiceImpl.getKilometrageByDate(historyDatesRequest);
        assertEquals(1, actualKilometrageByDate.size());
        assertEquals(10.0d, actualKilometrageByDate.get(0));
        verify(historyRepository).getKmByDate((Long) any(), (Date) any());
        verify(carRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDateInterval(HistoryIntervalsRequest)}
     */
    @Test
    void testGetKilometrageByDateInterval() {
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
        Optional<Car> ofResult = Optional.of(car);
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        assertTrue(historyServiceImpl.getKilometrageByDateInterval(new HistoryIntervalsRequest(123L, new ArrayList<>()))
                .isEmpty());
        verify(carRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDateInterval(HistoryIntervalsRequest)}
     */
    @Test
    void testGetKilometrageByDateInterval2() {
        when(carRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> historyServiceImpl.getKilometrageByDateInterval(new HistoryIntervalsRequest(123L, new ArrayList<>())));
        verify(carRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link HistoryServiceImpl#getKilometrageByDateInterval(HistoryIntervalsRequest)}
     */
    @Test
    void testGetKilometrageByDateInterval3() {
        when(historyRepository.getKmByDateInterval((Long) any(), (Date) any(), (Date) any())).thenReturn(10.0d);

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
        Optional<Car> ofResult = Optional.of(car);
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        Date date = mock(Date.class);
        when(date.getTime()).thenReturn(10L);
        Date date1 = mock(Date.class);
        when(date1.getTime()).thenReturn(10L);
        DateInterval e = new DateInterval(date, date1);

        ArrayList<DateInterval> dateIntervalList = new ArrayList<>();
        dateIntervalList.add(e);
        List<Double> actualKilometrageByDateInterval = historyServiceImpl
                .getKilometrageByDateInterval(new HistoryIntervalsRequest(123L, dateIntervalList));
        assertEquals(1, actualKilometrageByDateInterval.size());
        assertEquals(10.0d, actualKilometrageByDateInterval.get(0));
        verify(historyRepository).getKmByDateInterval((Long) any(), (Date) any(), (Date) any());
        verify(carRepository).findById((Long) any());
        verify(date).getTime();
    }
}

