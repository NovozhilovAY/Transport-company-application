package com.example.transportcompanyapplication.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.HistoryDatesRequest;
import com.example.transportcompanyapplication.dto.HistoryIntervalsRequest;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.service.HistoryServiceImpl;
import com.example.transportcompanyapplication.service.api.HistoryService;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

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

@ContextConfiguration(classes = {HistoryController.class})
@ExtendWith(SpringExtension.class)
class HistoryControllerTest {
    @Autowired
    private HistoryController historyController;

    @MockBean
    private HistoryService historyService;

    /**
     * Method under test: {@link HistoryController#getKilometrageByDate(HistoryDatesRequest)}
     */
    @Test
    void testGetKilometrageByDate() throws Exception {
        when(historyService.getKilometrageByDate((HistoryDatesRequest) any())).thenReturn(new ArrayList<>());

        HistoryDatesRequest historyDatesRequest = new HistoryDatesRequest();
        historyDatesRequest.setDates(new ArrayList<>());
        historyDatesRequest.setId(123L);
        String content = (new ObjectMapper()).writeValueAsString(historyDatesRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/history/date")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(historyController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link HistoryController#getKilometrageByDateInterval(HistoryIntervalsRequest)}
     */
    @Test
    void testGetKilometrageByDateInterval() {
        //   Diffblue Cover was unable to write a Spring test,
        //   so wrote a non-Spring test instead.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   org.springframework.http.converter.HttpMessageConversionException: Type definition error: [simple type, class com.example.transportcompanyapplication.dto.HistoryIntervalsRequest]; nested exception is com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.transportcompanyapplication.dto.HistoryIntervalsRequest` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (PushbackInputStream); line: 1, column: 2]
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   com.fasterxml.jackson.databind.exc.InvalidDefinitionException: Cannot construct instance of `com.example.transportcompanyapplication.dto.HistoryIntervalsRequest` (no Creators, like default constructor, exist): cannot deserialize from Object value (no delegate- or property-based Creator)
        //    at [Source: (PushbackInputStream); line: 1, column: 2]
        //       at com.fasterxml.jackson.databind.exc.InvalidDefinitionException.from(InvalidDefinitionException.java:67)
        //       at com.fasterxml.jackson.databind.DeserializationContext.reportBadDefinition(DeserializationContext.java:1904)
        //       at com.fasterxml.jackson.databind.DatabindContext.reportBadDefinition(DatabindContext.java:400)
        //       at com.fasterxml.jackson.databind.DeserializationContext.handleMissingInstantiator(DeserializationContext.java:1349)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializerBase.deserializeFromObjectUsingNonDefault(BeanDeserializerBase.java:1415)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserializeFromObject(BeanDeserializer.java:351)
        //       at com.fasterxml.jackson.databind.deser.BeanDeserializer.deserialize(BeanDeserializer.java:184)
        //       at com.fasterxml.jackson.databind.deser.DefaultDeserializationContext.readRootValue(DefaultDeserializationContext.java:322)
        //       at com.fasterxml.jackson.databind.ObjectMapper._readMapAndClose(ObjectMapper.java:4674)
        //       at com.fasterxml.jackson.databind.ObjectMapper.readValue(ObjectMapper.java:3682)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:681)
        //       at javax.servlet.http.HttpServlet.service(HttpServlet.java:764)
        //   See https://diff.blue/R013 to resolve this issue.

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
        CarRepository carRepository = mock(CarRepository.class);
        when(carRepository.findById((Long) any())).thenReturn(Optional.of(car));
        HistoryController historyController = new HistoryController(
                new HistoryServiceImpl(mock(HistoryRepository.class), carRepository));
        assertTrue(historyController.getKilometrageByDateInterval(new HistoryIntervalsRequest(123L, new ArrayList<>()))
                .isEmpty());
        verify(carRepository).findById((Long) any());
    }
}

