package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.service.api.FinancialReportService;
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

@ContextConfiguration(classes = {FinancialReportController.class})
@ExtendWith(SpringExtension.class)
class FinancialReportControllerTest {
    @Autowired
    private FinancialReportController financialReportController;

    @MockBean
    private FinancialReportService financialReportService;

    /**
     * Method under test: {@link FinancialReportController#getFinancialReportData()}
     */
    @Test
    void testGetFinancialReportData() throws Exception {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        when(financialReportService.getFinancialReportData()).thenReturn(financialReportData);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/financial-report/data");
        MockMvcBuilders.standaloneSetup(financialReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"numWorkingDays\":10,\"to1Cost\":1,\"to2Cost\":1,\"krCost\":1}"));
    }

    /**
     * Method under test: {@link FinancialReportController#getFinancialReport()}
     */
    @Test
    void testGetFinancialReport() throws Exception {
        when(financialReportService.getFinancialReport()).thenReturn(new FinancialReport());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/financial-report");
        MockMvcBuilders.standaloneSetup(financialReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"carFinancialReports\":null,\"totalKilometrage\":null,\"totalNumOfTo1\":null,\"totalNumOfTo2\":null,"
                                        + "\"totalNumOfKr\":null,\"totalCostTo1\":null,\"totalCostTo2\":null,\"totalCostKr\":null,\"totalCosts\":null}"));
    }

    /**
     * Method under test: {@link FinancialReportController#updateFinancialReportData(FinancialReportData)}
     */
    @Test
    void testUpdateFinancialReportData() throws Exception {
        FinancialReportData financialReportData = new FinancialReportData();
        financialReportData.setId(123L);
        financialReportData.setKrCost(1);
        financialReportData.setNumWorkingDays(10);
        financialReportData.setTo1Cost(1);
        financialReportData.setTo2Cost(1);
        when(financialReportService.updateReportData((FinancialReportData) any())).thenReturn(financialReportData);

        FinancialReportData financialReportData1 = new FinancialReportData();
        financialReportData1.setId(123L);
        financialReportData1.setKrCost(1);
        financialReportData1.setNumWorkingDays(10);
        financialReportData1.setTo1Cost(1);
        financialReportData1.setTo2Cost(1);
        String content = (new ObjectMapper()).writeValueAsString(financialReportData1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/api/financial-report/data")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        MockMvcBuilders.standaloneSetup(financialReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":123,\"numWorkingDays\":10,\"to1Cost\":1,\"to2Cost\":1,\"krCost\":1}"));
    }
}

