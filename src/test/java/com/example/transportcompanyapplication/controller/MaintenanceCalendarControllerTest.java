package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.CarsYearMaintCalendar;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.service.api.MaintenanceCalendarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MaintenanceCalendarController.class})
@ExtendWith(SpringExtension.class)
class MaintenanceCalendarControllerTest {
    @Autowired
    private MaintenanceCalendarController maintenanceCalendarController;

    @MockBean
    private MaintenanceCalendarService maintenanceCalendarService;

    /**
     * Method under test: {@link MaintenanceCalendarController#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar() throws Exception {
        when(maintenanceCalendarService.getCarsYearMaintCalendar()).thenReturn(new CarsYearMaintCalendar());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/calendar/maint-cars");
        MockMvcBuilders.standaloneSetup(maintenanceCalendarController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"calendar\":null}"));
    }

    /**
     * Method under test: {@link MaintenanceCalendarController#getCarsYearMaintCalendar()}
     */
    @Test
    void testGetCarsYearMaintCalendar2() throws Exception {
        when(maintenanceCalendarService.getCarsYearMaintCalendar()).thenReturn(new CarsYearMaintCalendar());
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/api/calendar/maint-cars");
        getResult.characterEncoding("Encoding");
        MockMvcBuilders.standaloneSetup(maintenanceCalendarController)
                .build()
                .perform(getResult)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"calendar\":null}"));
    }

    /**
     * Method under test: {@link MaintenanceCalendarController#getMaintDates(Long)}
     */
    @Test
    void testGetMaintDates() throws Exception {
        when(maintenanceCalendarService.getNextMaintDates((Long) any()))
                .thenReturn(new NextMaintDates("2020-03-01", "2020-03-01", "2020-03-01"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/calendar/next-maint-dates/{carId}", 123L);
        MockMvcBuilders.standaloneSetup(maintenanceCalendarController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"nextTo1Date\":\"2020-03-01\",\"nextTo2Date\":\"2020-03-01\",\"nextKrDate\":\"2020-03-01\"}"));
    }
}

