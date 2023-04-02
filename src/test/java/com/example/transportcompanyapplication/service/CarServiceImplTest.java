package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.correcting.model.CityType;
import com.example.transportcompanyapplication.correcting.model.ClimateType;
import com.example.transportcompanyapplication.correcting.model.ReliefType;
import com.example.transportcompanyapplication.correcting.model.RoadType;
import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.CorrectingRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.util.PatchMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CarServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CarServiceImplTest {
    @MockBean
    private CarRepository carRepository;

    @Autowired
    private CarServiceImpl carServiceImpl;

    @MockBean
    private CorrectingRepository correctingRepository;

    @MockBean
    private HistoryRepository historyRepository;

    @MockBean
    private PatchMapper<Car> patchMapper;

    /**
     * Method under test: {@link CarServiceImpl#save(Car)}
     */
    @Test
    void testSave() {
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
        when(carRepository.save((Car) any())).thenReturn(car);
        when(carRepository.findAll()).thenReturn(new ArrayList<>());

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);

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
        assertSame(car, carServiceImpl.save(car1));
        verify(carRepository).save((Car) any());
        verify(carRepository).findAll();
        verify(correctingRepository).findById((Long) any());
        assertEquals(0.0d, car1.getAvgKilometrage().doubleValue());
        assertEquals(10.0d, car1.getKmBeforeTo2().doubleValue());
        assertEquals(10.0d, car1.getKmBeforeTo1().doubleValue());
        assertEquals(10.0d, car1.getKmBeforeKr().doubleValue());
        assertEquals(10.0d, car1.getFactTo2().doubleValue());
        assertEquals(10.0d, car1.getFactTo1().doubleValue());
        assertEquals(10.0d, car1.getFactKr().doubleValue());
    }

    /**
     * Method under test: {@link CarServiceImpl#save(Car)}
     */
    @Test
    void testSave2() {
        when(carRepository.save((Car) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(carRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);

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
        assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.save(car));
        verify(carRepository).findAll();
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#save(Car)}
     */
    @Test
    void testSave3() {
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
        car1.setAvgKilometrage(1.0d);
        car1.setBrand("Brand");
        car1.setDriver(driver1);
        car1.setFactKr(1.0d);
        car1.setFactTo1(1.0d);
        car1.setFactTo2(1.0d);
        car1.setId(123L);
        car1.setKilometrage(1.0d);
        car1.setKmBeforeKr(1.0d);
        car1.setKmBeforeTo1(1.0d);
        car1.setKmBeforeTo2(1.0d);
        car1.setLatitude(1.0d);
        car1.setLicensePlate("License Plate");
        car1.setLongitude(1.0d);
        car1.setModel("Model");
        car1.setNormativeKr(1.0d);
        car1.setNormativeTo1(1.0d);
        car1.setNormativeTo2(1.0d);
        car1.setYear(1);

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car1);
        when(carRepository.save((Car) any())).thenReturn(car);
        when(carRepository.findAll()).thenReturn(carList);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);

        Driver driver2 = new Driver();
        driver2.setDrivingLicense("Driving License");
        driver2.setFirstName("Jane");
        driver2.setId(123L);
        driver2.setLastName("Doe");
        driver2.setMiddleName("Middle Name");

        Car car2 = new Car();
        car2.setAvgKilometrage(10.0d);
        car2.setBrand("Brand");
        car2.setDriver(driver2);
        car2.setFactKr(10.0d);
        car2.setFactTo1(10.0d);
        car2.setFactTo2(10.0d);
        car2.setId(123L);
        car2.setKilometrage(10.0d);
        car2.setKmBeforeKr(10.0d);
        car2.setKmBeforeTo1(10.0d);
        car2.setKmBeforeTo2(10.0d);
        car2.setLatitude(10.0d);
        car2.setLicensePlate("License Plate");
        car2.setLongitude(10.0d);
        car2.setModel("Model");
        car2.setNormativeKr(10.0d);
        car2.setNormativeTo1(10.0d);
        car2.setNormativeTo2(10.0d);
        car2.setYear(1);
        assertSame(car, carServiceImpl.save(car2));
        verify(carRepository).save((Car) any());
        verify(carRepository).findAll();
        verify(correctingRepository).findById((Long) any());
        assertEquals(1.0d, car2.getAvgKilometrage().doubleValue());
        assertEquals(10.0d, car2.getKmBeforeTo2().doubleValue());
        assertEquals(10.0d, car2.getKmBeforeTo1().doubleValue());
        assertEquals(10.0d, car2.getKmBeforeKr().doubleValue());
        assertEquals(10.0d, car2.getFactTo2().doubleValue());
        assertEquals(10.0d, car2.getFactTo1().doubleValue());
        assertEquals(10.0d, car2.getFactKr().doubleValue());
    }

    /**
     * Method under test: {@link CarServiceImpl#save(Car)}
     */
    @Test
    void testSave4() {
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
        car1.setAvgKilometrage(1.0d);
        car1.setBrand("Brand");
        car1.setDriver(driver1);
        car1.setFactKr(1.0d);
        car1.setFactTo1(1.0d);
        car1.setFactTo2(1.0d);
        car1.setId(123L);
        car1.setKilometrage(1.0d);
        car1.setKmBeforeKr(1.0d);
        car1.setKmBeforeTo1(1.0d);
        car1.setKmBeforeTo2(1.0d);
        car1.setLatitude(1.0d);
        car1.setLicensePlate("License Plate");
        car1.setLongitude(1.0d);
        car1.setModel("Model");
        car1.setNormativeKr(1.0d);
        car1.setNormativeTo1(1.0d);
        car1.setNormativeTo2(1.0d);
        car1.setYear(1);

        Driver driver2 = new Driver();
        driver2.setDrivingLicense("Driving License");
        driver2.setFirstName("Jane");
        driver2.setId(123L);
        driver2.setLastName("Doe");
        driver2.setMiddleName("Middle Name");

        Car car2 = new Car();
        car2.setAvgKilometrage(1.0d);
        car2.setBrand("Brand");
        car2.setDriver(driver2);
        car2.setFactKr(1.0d);
        car2.setFactTo1(1.0d);
        car2.setFactTo2(1.0d);
        car2.setId(123L);
        car2.setKilometrage(1.0d);
        car2.setKmBeforeKr(1.0d);
        car2.setKmBeforeTo1(1.0d);
        car2.setKmBeforeTo2(1.0d);
        car2.setLatitude(1.0d);
        car2.setLicensePlate("License Plate");
        car2.setLongitude(1.0d);
        car2.setModel("Model");
        car2.setNormativeKr(1.0d);
        car2.setNormativeTo1(1.0d);
        car2.setNormativeTo2(1.0d);
        car2.setYear(1);

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car2);
        carList.add(car1);
        when(carRepository.save((Car) any())).thenReturn(car);
        when(carRepository.findAll()).thenReturn(carList);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);

        Driver driver3 = new Driver();
        driver3.setDrivingLicense("Driving License");
        driver3.setFirstName("Jane");
        driver3.setId(123L);
        driver3.setLastName("Doe");
        driver3.setMiddleName("Middle Name");

        Car car3 = new Car();
        car3.setAvgKilometrage(10.0d);
        car3.setBrand("Brand");
        car3.setDriver(driver3);
        car3.setFactKr(10.0d);
        car3.setFactTo1(10.0d);
        car3.setFactTo2(10.0d);
        car3.setId(123L);
        car3.setKilometrage(10.0d);
        car3.setKmBeforeKr(10.0d);
        car3.setKmBeforeTo1(10.0d);
        car3.setKmBeforeTo2(10.0d);
        car3.setLatitude(10.0d);
        car3.setLicensePlate("License Plate");
        car3.setLongitude(10.0d);
        car3.setModel("Model");
        car3.setNormativeKr(10.0d);
        car3.setNormativeTo1(10.0d);
        car3.setNormativeTo2(10.0d);
        car3.setYear(1);
        assertSame(car, carServiceImpl.save(car3));
        verify(carRepository).save((Car) any());
        verify(carRepository).findAll();
        verify(correctingRepository).findById((Long) any());
        assertEquals(1.0d, car3.getAvgKilometrage().doubleValue());
        assertEquals(10.0d, car3.getKmBeforeTo2().doubleValue());
        assertEquals(10.0d, car3.getKmBeforeTo1().doubleValue());
        assertEquals(10.0d, car3.getKmBeforeKr().doubleValue());
        assertEquals(10.0d, car3.getFactTo2().doubleValue());
        assertEquals(10.0d, car3.getFactTo1().doubleValue());
        assertEquals(10.0d, car3.getFactKr().doubleValue());
    }

    /**
     * Method under test: {@link CarServiceImpl#save(Car)}
     */
    @Test
    void testSave5() {
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
        when(carRepository.save((Car) any())).thenReturn(car);
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        CorrectingData correctingData = mock(CorrectingData.class);
        when(correctingData.getClimateType()).thenThrow(new ResourceNotFoundException("An error occurred"));
        when(correctingData.getCityType()).thenReturn(CityType.OUT);
        when(correctingData.getReliefType()).thenReturn(ReliefType.R1);
        when(correctingData.getRoadType()).thenReturn(RoadType.D1);
        doNothing().when(correctingData).setCityType((CityType) any());
        doNothing().when(correctingData).setClimateType((ClimateType) any());
        doNothing().when(correctingData).setId((Long) any());
        doNothing().when(correctingData).setReliefType((ReliefType) any());
        doNothing().when(correctingData).setRoadType((RoadType) any());
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);

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
        assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.save(car1));
        verify(correctingRepository).findById((Long) any());
        verify(correctingData).getCityType();
        verify(correctingData).getClimateType();
        verify(correctingData).getReliefType();
        verify(correctingData).getRoadType();
        verify(correctingData).setCityType((CityType) any());
        verify(correctingData).setClimateType((ClimateType) any());
        verify(correctingData).setId((Long) any());
        verify(correctingData).setReliefType((ReliefType) any());
        verify(correctingData).setRoadType((RoadType) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateCoordinates(Long, NewCoordinatesOfCar)}
     */
    @Test
    void testUpdateCoordinates() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        Car actualUpdateCoordinatesResult = carServiceImpl.updateCoordinates(123L, new NewCoordinatesOfCar());
        assertSame(car, actualUpdateCoordinatesResult);
        assertNull(actualUpdateCoordinatesResult.getLongitude());
        assertNull(actualUpdateCoordinatesResult.getLatitude());
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateCoordinates(Long, NewCoordinatesOfCar)}
     */
    @Test
    void testUpdateCoordinates2() {
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
        doThrow(new ResourceNotFoundException("An error occurred")).when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        assertThrows(ResourceNotFoundException.class,
                () -> carServiceImpl.updateCoordinates(123L, new NewCoordinatesOfCar()));
        verify(carRepository).findById((Long) any());
        verify(carRepository).update((Car) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateCoordinates(Long, NewCoordinatesOfCar)}
     */
    @Test
    void testUpdateCoordinates3() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
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
        Optional<Car> ofResult = Optional.of(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.updateCoordinates(123L, new NewCoordinatesOfCar());
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
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
        verify(car, atLeast(1)).setLatitude((Double) any());
        verify(car).setLicensePlate((String) any());
        verify(car, atLeast(1)).setLongitude((Double) any());
        verify(car).setModel((String) any());
        verify(car).setNormativeKr((Double) any());
        verify(car).setNormativeTo1((Double) any());
        verify(car).setNormativeTo2((Double) any());
        verify(car).setYear((Integer) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#doTo1(Long)}
     */
    @Test
    void testDoTo1() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        Car actualDoTo1Result = carServiceImpl.doTo1(123L);
        assertSame(car, actualDoTo1Result);
        assertEquals(10.0d, actualDoTo1Result.getKmBeforeTo1().doubleValue());
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#doTo1(Long)}
     */
    @Test
    void testDoTo13() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getId()).thenReturn(123L);
        doNothing().when(car).doTo1();
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
        Optional<Car> ofResult = Optional.of(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.doTo1(123L);
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
        verify(car).getId();
        verify(car).doTo1();
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
     * Method under test: {@link CarServiceImpl#doTo2(Long)}
     */
    @Test
    void testDoTo2() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        Car actualDoTo2Result = carServiceImpl.doTo2(123L);
        assertSame(car, actualDoTo2Result);
        assertEquals(10.0d, actualDoTo2Result.getKmBeforeTo2().doubleValue());
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#doTo2(Long)}
     */
    @Test
    void testDoTo23() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getId()).thenReturn(123L);
        doNothing().when(car).doTo2();
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
        Optional<Car> ofResult = Optional.of(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.doTo2(123L);
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
        verify(car).getId();
        verify(car).doTo2();
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
     * Method under test: {@link CarServiceImpl#doKr(Long)}
     */
    @Test
    void testDoKr() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        Car actualDoKrResult = carServiceImpl.doKr(123L);
        assertSame(car, actualDoKrResult);
        assertEquals(10.0d, actualDoKrResult.getKmBeforeKr().doubleValue());
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
    }


    /**
     * Method under test: {@link CarServiceImpl#doKr(Long)}
     */
    @Test
    void testDoKr3() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getId()).thenReturn(123L);
        doNothing().when(car).doKr();
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
        Optional<Car> ofResult = Optional.of(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.doKr(123L);
        verify(carRepository, atLeast(1)).findById((Long) any());
        verify(carRepository).update((Car) any());
        verify(car).getId();
        verify(car).doKr();
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
     * Method under test: {@link CarServiceImpl#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage() {
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
        when(carRepository.save((Car) any())).thenReturn(car1);
        when(carRepository.getById((Long) any())).thenReturn(car);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.correctCarKilometrage(123L);
        verify(carRepository).getById((Long) any());
        verify(carRepository).save((Car) any());
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage3() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        doNothing().when(car).correctKilometrage((Double) any(), (Double) any(), (Double) any(), (Double) any());
        doNothing().when(car).setAvgKilometrage((Double) any());
        doNothing().when(car).setBrand((String) any());
        doNothing().when(car).setDefaultKmBefore();
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
        when(carRepository.save((Car) any())).thenReturn(car1);
        when(carRepository.getById((Long) any())).thenReturn(car);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.correctCarKilometrage(123L);
        verify(carRepository).getById((Long) any());
        verify(carRepository).save((Car) any());
        verify(car).correctKilometrage((Double) any(), (Double) any(), (Double) any(), (Double) any());
        verify(car).setAvgKilometrage((Double) any());
        verify(car).setBrand((String) any());
        verify(car).setDefaultKmBefore();
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
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#correctAllCarKilometrage()}
     */
    @Test
    void testCorrectAllCarKilometrage() {
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        carServiceImpl.correctAllCarKilometrage();
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link CarServiceImpl#correctAllCarKilometrage()}
     */
    @Test
    void testCorrectAllCarKilometrage2() {
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

        Driver driver2 = new Driver();
        driver2.setDrivingLicense("Driving License");
        driver2.setFirstName("Jane");
        driver2.setId(123L);
        driver2.setLastName("Doe");
        driver2.setMiddleName("Middle Name");

        Car car2 = new Car();
        car2.setAvgKilometrage(10.0d);
        car2.setBrand("Brand");
        car2.setDriver(driver2);
        car2.setFactKr(10.0d);
        car2.setFactTo1(10.0d);
        car2.setFactTo2(10.0d);
        car2.setId(123L);
        car2.setKilometrage(10.0d);
        car2.setKmBeforeKr(10.0d);
        car2.setKmBeforeTo1(10.0d);
        car2.setKmBeforeTo2(10.0d);
        car2.setLatitude(10.0d);
        car2.setLicensePlate("License Plate");
        car2.setLongitude(10.0d);
        car2.setModel("Model");
        car2.setNormativeKr(10.0d);
        car2.setNormativeTo1(10.0d);
        car2.setNormativeTo2(10.0d);
        car2.setYear(1);
        when(carRepository.save((Car) any())).thenReturn(car2);
        when(carRepository.getById((Long) any())).thenReturn(car1);
        when(carRepository.findAll()).thenReturn(carList);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.correctAllCarKilometrage();
        verify(carRepository).getById((Long) any());
        verify(carRepository).save((Car) any());
        verify(carRepository).findAll();
        verify(correctingRepository).findById((Long) any());
    }


    /**
     * Method under test: {@link CarServiceImpl#correctAllCarKilometrage()}
     */
    @Test
    void testCorrectAllCarKilometrage4() {
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

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");
        Car car1 = mock(Car.class);
        doNothing().when(car1).correctKilometrage((Double) any(), (Double) any(), (Double) any(), (Double) any());
        doNothing().when(car1).setAvgKilometrage((Double) any());
        doNothing().when(car1).setBrand((String) any());
        doNothing().when(car1).setDefaultKmBefore();
        doNothing().when(car1).setDriver((Driver) any());
        doNothing().when(car1).setFactKr((Double) any());
        doNothing().when(car1).setFactTo1((Double) any());
        doNothing().when(car1).setFactTo2((Double) any());
        doNothing().when(car1).setId((Long) any());
        doNothing().when(car1).setKilometrage((Double) any());
        doNothing().when(car1).setKmBeforeKr((Double) any());
        doNothing().when(car1).setKmBeforeTo1((Double) any());
        doNothing().when(car1).setKmBeforeTo2((Double) any());
        doNothing().when(car1).setLatitude((Double) any());
        doNothing().when(car1).setLicensePlate((String) any());
        doNothing().when(car1).setLongitude((Double) any());
        doNothing().when(car1).setModel((String) any());
        doNothing().when(car1).setNormativeKr((Double) any());
        doNothing().when(car1).setNormativeTo1((Double) any());
        doNothing().when(car1).setNormativeTo2((Double) any());
        doNothing().when(car1).setYear((Integer) any());
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

        Driver driver2 = new Driver();
        driver2.setDrivingLicense("Driving License");
        driver2.setFirstName("Jane");
        driver2.setId(123L);
        driver2.setLastName("Doe");
        driver2.setMiddleName("Middle Name");

        Car car2 = new Car();
        car2.setAvgKilometrage(10.0d);
        car2.setBrand("Brand");
        car2.setDriver(driver2);
        car2.setFactKr(10.0d);
        car2.setFactTo1(10.0d);
        car2.setFactTo2(10.0d);
        car2.setId(123L);
        car2.setKilometrage(10.0d);
        car2.setKmBeforeKr(10.0d);
        car2.setKmBeforeTo1(10.0d);
        car2.setKmBeforeTo2(10.0d);
        car2.setLatitude(10.0d);
        car2.setLicensePlate("License Plate");
        car2.setLongitude(10.0d);
        car2.setModel("Model");
        car2.setNormativeKr(10.0d);
        car2.setNormativeTo1(10.0d);
        car2.setNormativeTo2(10.0d);
        car2.setYear(1);
        when(carRepository.save((Car) any())).thenReturn(car2);
        when(carRepository.getById((Long) any())).thenReturn(car1);
        when(carRepository.findAll()).thenReturn(carList);

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        carServiceImpl.correctAllCarKilometrage();
        verify(carRepository).getById((Long) any());
        verify(carRepository).save((Car) any());
        verify(carRepository).findAll();
        verify(car1).correctKilometrage((Double) any(), (Double) any(), (Double) any(), (Double) any());
        verify(car1).setAvgKilometrage((Double) any());
        verify(car1).setBrand((String) any());
        verify(car1).setDefaultKmBefore();
        verify(car1).setDriver((Driver) any());
        verify(car1).setFactKr((Double) any());
        verify(car1).setFactTo1((Double) any());
        verify(car1).setFactTo2((Double) any());
        verify(car1).setId((Long) any());
        verify(car1).setKilometrage((Double) any());
        verify(car1).setKmBeforeKr((Double) any());
        verify(car1).setKmBeforeTo1((Double) any());
        verify(car1).setKmBeforeTo2((Double) any());
        verify(car1).setLatitude((Double) any());
        verify(car1).setLicensePlate((String) any());
        verify(car1).setLongitude((Double) any());
        verify(car1).setModel((String) any());
        verify(car1).setNormativeKr((Double) any());
        verify(car1).setNormativeTo1((Double) any());
        verify(car1).setNormativeTo2((Double) any());
        verify(car1).setYear((Integer) any());
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage() {
        when(carRepository.findAll()).thenReturn(new ArrayList<>());
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository).findAll();
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage2() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any())).thenReturn(10.0d);
        when(historyRepository.getNumOfDays((Long) any())).thenReturn(10);
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository).findAll();
        verify(carRepository).update((Car) any());
        verify(historyRepository).getAllKilometrage((Long) any());
        verify(historyRepository).getNumOfDays((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage3() {
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any()))
                .thenThrow(new ResourceNotFoundException("An error occurred"));
        when(historyRepository.getNumOfDays((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> carServiceImpl.updateAvgKilometrage());
        verify(carRepository).findAll();
        verify(historyRepository).getNumOfDays((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage4() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getId()).thenReturn(123L);
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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any())).thenReturn(10.0d);
        when(historyRepository.getNumOfDays((Long) any())).thenReturn(10);
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository).findAll();
        verify(carRepository).update((Car) any());
        verify(car, atLeast(1)).getId();
        verify(car, atLeast(1)).setAvgKilometrage((Double) any());
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
        verify(historyRepository).getAllKilometrage((Long) any());
        verify(historyRepository).getNumOfDays((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage5() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getAvgKilometrage()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any())).thenReturn(10.0d);
        when(historyRepository.getNumOfDays((Long) any())).thenReturn(0);
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository, atLeast(1)).findAll();
        verify(carRepository).update((Car) any());
        verify(car, atLeast(1)).getAvgKilometrage();
        verify(car, atLeast(1)).getId();
        verify(car, atLeast(1)).setAvgKilometrage((Double) any());
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
        verify(historyRepository).getAllKilometrage((Long) any());
        verify(historyRepository).getNumOfDays((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage6() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getAvgKilometrage()).thenReturn(10.0d);
        when(car.getId()).thenReturn(123L);
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
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any())).thenReturn(10.0d);
        when(historyRepository.getNumOfDays((Long) any())).thenReturn(0);
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository, atLeast(1)).findAll();
        verify(carRepository, atLeast(1)).update((Car) any());
        verify(car, atLeast(1)).getAvgKilometrage();
        verify(car, atLeast(1)).getId();
        verify(car, atLeast(1)).setAvgKilometrage((Double) any());
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
        verify(historyRepository, atLeast(1)).getAllKilometrage((Long) any());
        verify(historyRepository, atLeast(1)).getNumOfDays((Long) any());
    }

    /**
     * Method under test: {@link CarServiceImpl#updateAvgKilometrage()}
     */
    @Test
    void testUpdateAvgKilometrage7() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Car car = mock(Car.class);
        when(car.getAvgKilometrage()).thenReturn(null);
        when(car.getId()).thenReturn(123L);
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

        ArrayList<Car> carList = new ArrayList<>();
        carList.add(car);
        doNothing().when(carRepository).update((Car) any());
        when(carRepository.findAll()).thenReturn(carList);
        when(historyRepository.getAllKilometrage((Long) any())).thenReturn(10.0d);
        when(historyRepository.getNumOfDays((Long) any())).thenReturn(0);
        carServiceImpl.updateAvgKilometrage();
        verify(carRepository, atLeast(1)).findAll();
        verify(carRepository).update((Car) any());
        verify(car).getAvgKilometrage();
        verify(car, atLeast(1)).getId();
        verify(car, atLeast(1)).setAvgKilometrage((Double) any());
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
        verify(historyRepository).getAllKilometrage((Long) any());
        verify(historyRepository).getNumOfDays((Long) any());
    }
}

