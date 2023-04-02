package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.FinancialReportRepository;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {FinancialReportServiceImpl.class})
@ExtendWith(SpringExtension.class)
class FinancialReportServiceImplTest {
    @MockBean
    private CarRepository carRepository;

    @MockBean
    private FinancialReportRepository financialReportRepository;

    @Autowired
    private FinancialReportServiceImpl financialReportServiceImpl;

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReportData()}
     */
    @Test
    void testGetFinancialReportData() {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        Optional<FinancialReportData> ofResult = Optional.of(financialReportData);
        when(financialReportRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(financialReportData, financialReportServiceImpl.getFinancialReportData());
        verify(financialReportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReportData()}
     */
    @Test
    void testGetFinancialReportData2() {
        when(financialReportRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> financialReportServiceImpl.getFinancialReportData());
        verify(financialReportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReportData()}
     */
    @Test
    void testGetFinancialReportData3() {
        when(financialReportRepository.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> financialReportServiceImpl.getFinancialReportData());
        verify(financialReportRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#updateReportData(FinancialReportData)}
     */
    @Test
    void testUpdateReportData() {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        Optional<FinancialReportData> ofResult = Optional.of(financialReportData);
        when(financialReportRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(financialReportRepository).update((FinancialReportData) any());

        FinancialReportData financialReportData1 = new FinancialReportData();
        financialReportData1.setId(123L);
        financialReportData1.setKrCost(1);
        financialReportData1.setNumWorkingDays(10);
        financialReportData1.setTo1Cost(1);
        financialReportData1.setTo2Cost(1);
        assertSame(financialReportData, financialReportServiceImpl.updateReportData(financialReportData1));
        verify(financialReportRepository).findById((Long) any());
        verify(financialReportRepository).update((FinancialReportData) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#updateReportData(FinancialReportData)}
     */
    @Test
    void testUpdateReportData2() {
        when(financialReportRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(financialReportRepository).update((FinancialReportData) any());

        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        assertThrows(ResourceNotFoundException.class,
                () -> financialReportServiceImpl.updateReportData(financialReportData));
        verify(financialReportRepository).findById((Long) any());
        verify(financialReportRepository).update((FinancialReportData) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#updateReportData(FinancialReportData)}
     */
    @Test
    void testUpdateReportData3() {
        when(financialReportRepository.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        doThrow(new ResourceNotFoundException("An error occurred")).when(financialReportRepository)
                .update((FinancialReportData) any());

        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        assertThrows(ResourceNotFoundException.class,
                () -> financialReportServiceImpl.updateReportData(financialReportData));
        verify(financialReportRepository).update((FinancialReportData) any());
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport() {
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        FinancialReport actualFinancialReport = financialReportServiceImpl.getFinancialReport();
        assertTrue(actualFinancialReport.getCarFinancialReports().isEmpty());
        assertEquals(0, actualFinancialReport.getTotalNumOfTo2().intValue());
        assertEquals(0, actualFinancialReport.getTotalNumOfTo1().intValue());
        assertEquals(0, actualFinancialReport.getTotalNumOfKr().intValue());
        assertEquals(0.0d, actualFinancialReport.getTotalKilometrage().doubleValue());
        assertEquals(0, actualFinancialReport.getTotalCosts().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostTo2().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostTo1().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostKr().intValue());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport2() {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        Optional<FinancialReportData> ofResult = Optional.of(financialReportData);
        when(financialReportRepository.findById((Long) any())).thenReturn(ofResult);

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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findAll()).thenReturn(carList);
        FinancialReport actualFinancialReport = financialReportServiceImpl.getFinancialReport();
        assertEquals(1, actualFinancialReport.getCarFinancialReports().size());
        assertEquals(10, actualFinancialReport.getTotalNumOfTo2().intValue());
        assertEquals(10, actualFinancialReport.getTotalNumOfTo1().intValue());
        assertEquals(10, actualFinancialReport.getTotalNumOfKr().intValue());
        assertEquals(100.0d, actualFinancialReport.getTotalKilometrage().doubleValue());
        assertEquals(30, actualFinancialReport.getTotalCosts().intValue());
        assertEquals(10, actualFinancialReport.getTotalCostTo2().intValue());
        assertEquals(10, actualFinancialReport.getTotalCostTo1().intValue());
        assertEquals(10, actualFinancialReport.getTotalCostKr().intValue());
        verify(financialReportRepository).findById((Long) any());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport8() {
        when(financialReportRepository.findById((Long) any())).thenReturn(Optional.empty());

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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findAll()).thenReturn(carList);
        assertThrows(ResourceNotFoundException.class, () -> financialReportServiceImpl.getFinancialReport());
        verify(financialReportRepository).findById((Long) any());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport9() {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        Optional<FinancialReportData> ofResult = Optional.of(financialReportData);
        when(financialReportRepository.findById((Long) any())).thenReturn(ofResult);

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

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");

        Car car1 = new Car();
        car1.setAvgKilometrage(10.0d);
        car1.setBrand("Brand");
        car1.setDriver(driver1);
        car1.setFactKr(10.0d);
        car1.setFactTo1(10.0d);
        car1.setFactTo2(10.0d);
        car1.setId(123L);
        car1.setKilometrage(10.0d);
        car1.setKmBeforeKr(10.0d);
        car1.setKmBeforeTo1(10.0d);
        car1.setKmBeforeTo2(10.0d);
        car1.setLatitude(10.0d);
        car1.setLicensePlate("License Plate");
        car1.setLongitude(10.0d);
        car1.setModel("Model");
        car1.setNormativeKr(10.0d);
        car1.setNormativeTo1(10.0d);
        car1.setNormativeTo2(10.0d);
        car1.setYear(1);

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car1);
        carList.add(car);
        when(carRepository.findAll()).thenReturn(carList);
        FinancialReport actualFinancialReport = financialReportServiceImpl.getFinancialReport();
        assertEquals(2, actualFinancialReport.getCarFinancialReports().size());
        assertEquals(20, actualFinancialReport.getTotalNumOfTo2().intValue());
        assertEquals(20, actualFinancialReport.getTotalNumOfTo1().intValue());
        assertEquals(20, actualFinancialReport.getTotalNumOfKr().intValue());
        assertEquals(200.0d, actualFinancialReport.getTotalKilometrage().doubleValue());
        assertEquals(60, actualFinancialReport.getTotalCosts().intValue());
        assertEquals(20, actualFinancialReport.getTotalCostTo2().intValue());
        assertEquals(20, actualFinancialReport.getTotalCostTo1().intValue());
        assertEquals(20, actualFinancialReport.getTotalCostKr().intValue());
        verify(financialReportRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport10() {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        Optional<FinancialReportData> ofResult = Optional.of(financialReportData);
        when(financialReportRepository.findById((Long) any())).thenReturn(ofResult);

        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");

        Car car = new Car();
        car.setAvgKilometrage(null);
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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findAll()).thenReturn(carList);
        FinancialReport actualFinancialReport = financialReportServiceImpl.getFinancialReport();
        assertEquals(1, actualFinancialReport.getCarFinancialReports().size());
        assertEquals(0, actualFinancialReport.getTotalNumOfTo2().intValue());
        assertEquals(0, actualFinancialReport.getTotalNumOfTo1().intValue());
        assertEquals(0, actualFinancialReport.getTotalNumOfKr().intValue());
        assertEquals(0.0d, actualFinancialReport.getTotalKilometrage().doubleValue());
        assertEquals(0, actualFinancialReport.getTotalCosts().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostTo2().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostTo1().intValue());
        assertEquals(0, actualFinancialReport.getTotalCostKr().intValue());
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link FinancialReportServiceImpl#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport14() {
        when(financialReportRepository.findById((Long) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));

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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        when(carRepository.findAll()).thenReturn(carList);
        assertThrows(ResourceNotFoundException.class, () -> financialReportServiceImpl.getFinancialReport());
        verify(financialReportRepository).findById((Long) any());
        verify(carRepository).findAll();
    }
}

