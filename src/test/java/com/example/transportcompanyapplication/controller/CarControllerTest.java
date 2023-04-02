package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.service.api.CarService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {CarController.class})
@ExtendWith(SpringExtension.class)
class CarControllerTest {
    @Autowired
    private CarController carController;

    @MockBean
    private CarService carService;

    /**
     * Method under test: {@link CarController#updateCoordinates(Long, NewCoordinatesOfCar)}
     */
    @Test
    void testUpdateCoordinates() throws Exception {
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
        when(carService.updateCoordinates((Long) any(), (NewCoordinatesOfCar) any())).thenReturn(car);

        NewCoordinatesOfCar newCoordinatesOfCar = new NewCoordinatesOfCar();
        newCoordinatesOfCar.setLatitude(10.0d);
        newCoordinatesOfCar.setLongitude(10.0d);
        String content = (new ObjectMapper()).writeValueAsString(newCoordinatesOfCar);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/cars/coordinates/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#doTo1(Long)}
     */
    @Test
    void testDoTo1() throws Exception {
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
        when(carService.doTo1((Long) any())).thenReturn(car);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/cars/to-1/{id}", 123L);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#doTo2(Long)}
     */
    @Test
    void testDoTo2() throws Exception {
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
        when(carService.doTo2((Long) any())).thenReturn(car);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/cars/to-2/{id}", 123L);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#createCar(Car)}
     */
    @Test
    void testCreateCar() throws Exception {
        when(carService.findAll()).thenReturn(new ArrayList<>());

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
        String content = (new ObjectMapper()).writeValueAsString(car);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CarController#doKr(Long)}
     */
    @Test
    void testDoKr() throws Exception {
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
        when(carService.doKr((Long) any())).thenReturn(car);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/cars/kr/{id}", 123L);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#partialUpdate(Long, Car)}
     */
    @Test
    void testPartialUpdate() throws Exception {
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
        when(carService.partialUpdate((Car) any(), (Long) any())).thenReturn(car);

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
        String content = (new ObjectMapper()).writeValueAsString(car1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/cars/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#getAllCars()}
     */
    @Test
    void testGetAllCars() throws Exception {
        when(carService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars");
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CarController#getAllCars()}
     */
    @Test
    void testGetAllCars2() throws Exception {
        when(carService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/cars");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link CarController#getCarById(Long)}
     */
    @Test
    void testGetCarById() throws Exception {
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
        when(carService.findById((Long) any())).thenReturn(car);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars/{id}", 123L);
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"brand\":\"Brand\",\"model\":\"Model\",\"year\":1,\"kilometrage\":10.0,\"licensePlate\":\"License"
                                        + " Plate\",\"normativeTo1\":10.0,\"normativeTo2\":10.0,\"normativeKr\":10.0,\"factTo1\":10.0,\"factTo2\":10.0,"
                                        + "\"factKr\":10.0,\"kmBeforeTo1\":10.0,\"kmBeforeTo2\":10.0,\"kmBeforeKr\":10.0,\"latitude\":10.0,\"longitude\":10"
                                        + ".0,\"driver\":{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense"
                                        + "\":\"Driving License\"},\"avgKilometrage\":10.0}"));
    }

    /**
     * Method under test: {@link CarController#updateAvgKm()}
     */
    @Test
    void testUpdateAvgKm() throws Exception {
        doNothing().when(carService).updateAvgKilometrage();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/cars/update-avg-km");
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CarController#updateAvgKm()}
     */
    @Test
    void testUpdateAvgKm2() throws Exception {
        doNothing().when(carService).updateAvgKilometrage();
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/cars/update-avg-km");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(carController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}

