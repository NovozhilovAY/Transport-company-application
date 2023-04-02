package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.service.api.DriverService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DriverController.class})
@ExtendWith(SpringExtension.class)
class DriverControllerTest {
    @Autowired
    private DriverController driverController;

    @MockBean
    private DriverService driverService;

    /**
     * Method under test: {@link DriverController#createDriver(Driver)}
     */
    @Test
    void testCreateDriver() throws Exception {
        when(driverService.findAll()).thenReturn(new ArrayList<>());

        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        String content = (new ObjectMapper()).writeValueAsString(driver);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/drivers")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#getFreeDrivers()}
     */
    @Test
    void testGetFreeDrivers() throws Exception {
        when(driverService.getFreeDrivers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/drivers/free");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#getFreeDrivers()}
     */
    @Test
    void testGetFreeDrivers2() throws Exception {
        when(driverService.getFreeDrivers()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/drivers/free");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#partialUpdate(Long, Driver)}
     */
    @Test
    void testPartialUpdate() throws Exception {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        when(driverService.partialUpdate((Driver) any(), (Long) any())).thenReturn(driver);

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");
        String content = (new ObjectMapper()).writeValueAsString(driver1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.patch("/api/drivers/{id}", 123L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense\":\"Driving"
                                        + " License\"}"));
    }

    /**
     * Method under test: {@link DriverController#deleteDriver(Long)}
     */
    @Test
    void testDeleteDriver() throws Exception {
        doNothing().when(driverService).deleteById((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/api/drivers/{id}", 123L);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link DriverController#deleteDriver(Long)}
     */
    @Test
    void testDeleteDriver2() throws Exception {
        doNothing().when(driverService).deleteById((Long) any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/api/drivers/{id}", 123L);
        deleteResult.characterEncoding("Encoding");
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(deleteResult);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    /**
     * Method under test: {@link DriverController#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers() throws Exception {
        when(driverService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/drivers");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#getAllDrivers()}
     */
    @Test
    void testGetAllDrivers2() throws Exception {
        when(driverService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/drivers");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DriverController#getDriverById(Long)}
     */
    @Test
    void testGetDriverById() throws Exception {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        when(driverService.findById((Long) any())).thenReturn(driver);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/drivers/{id}", 123L);
        MockMvcBuilders.standaloneSetup(driverController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"lastName\":\"Doe\",\"firstName\":\"Jane\",\"middleName\":\"Middle Name\",\"drivingLicense\":\"Driving"
                                        + " License\"}"));
    }
}

