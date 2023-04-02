package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.correcting.model.CityType;
import com.example.transportcompanyapplication.correcting.model.ClimateType;
import com.example.transportcompanyapplication.correcting.model.ReliefType;
import com.example.transportcompanyapplication.correcting.model.RoadType;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.service.api.CorrectingService;
import com.fasterxml.jackson.databind.ObjectMapper;
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

@ContextConfiguration(classes = {CorrectingController.class})
@ExtendWith(SpringExtension.class)
class CorrectingControllerTest {
    @Autowired
    private CorrectingController correctingController;

    @MockBean
    private CorrectingService correctingService;

    /**
     * Method under test: {@link CorrectingController#getCorrectingData()}
     */
    @Test
    void testGetCorrectingData() throws Exception {
        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        when(correctingService.getCorrectingData()).thenReturn(correctingData);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/correcting");
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"cityType\":\"OUT\",\"climateType\":\"TEMPERATE\",\"reliefType\":\"R1\",\"roadType\":\"D1\"}"));
    }

    /**
     * Method under test: {@link CorrectingController#correctAllCarsKilometrage()}
     */
    @Test
    void testCorrectAllCarsKilometrage() throws Exception {
        doNothing().when(correctingService).correctAllCarKilometrage();
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/correcting/cars");
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CorrectingController#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage() throws Exception {
        doNothing().when(correctingService).correctCarKilometrage((Long) any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/correcting/car/{id}", 123L);
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CorrectingController#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage2() throws Exception {
        doNothing().when(correctingService).correctCarKilometrage((Long) any());
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/correcting/car/{id}", 123L);
        postResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CorrectingController#correctAllCarsKilometrage()}
     */
    @Test
    void testCorrectAllCarsKilometrage2() throws Exception {
        doNothing().when(correctingService).correctAllCarKilometrage();
        MockHttpServletRequestBuilder postResult = MockMvcRequestBuilders.post("/api/correcting/cars");
        postResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(postResult)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    /**
     * Method under test: {@link CorrectingController#setCorrectingData(CorrectingData)}
     */
    @Test
    void testSetCorrectingData() throws Exception {
        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        when(correctingService.getCorrectingData()).thenReturn(correctingData);

        CorrectingData correctingData1 = new CorrectingData();
        correctingData1.setCityType(CityType.OUT);
        correctingData1.setClimateType(ClimateType.TEMPERATE);
        correctingData1.setId(123L);
        correctingData1.setReliefType(ReliefType.R1);
        correctingData1.setRoadType(RoadType.D1);
        String content = (new ObjectMapper()).writeValueAsString(correctingData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/correcting")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(correctingController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":123,\"cityType\":\"OUT\",\"climateType\":\"TEMPERATE\",\"reliefType\":\"R1\",\"roadType\":\"D1\"}"));
    }
}

