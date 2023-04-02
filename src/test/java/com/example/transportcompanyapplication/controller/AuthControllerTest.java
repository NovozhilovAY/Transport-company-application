package com.example.transportcompanyapplication.controller;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.dto.AuthRequest;
import com.example.transportcompanyapplication.dto.RefreshRequest;
import com.example.transportcompanyapplication.service.AuthServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {AuthController.class})
@ExtendWith(SpringExtension.class)
class AuthControllerTest {
    @Autowired
    private AuthController authController;

    @MockBean
    private AuthServiceImpl authServiceImpl;

    /**
     * Method under test: {@link AuthController#login(AuthRequest)}
     */
    @Test
    void testLogin() throws Exception {
        when(authServiceImpl.login((AuthRequest) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        AuthRequest authRequest = new AuthRequest();
        authRequest.setPassword("iloveyou");
        authRequest.setUsername("janedoe");
        String content = (new ObjectMapper()).writeValueAsString(authRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController).build().perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }

    /**
     * Method under test: {@link AuthController#refresh(RefreshRequest)}
     */
    @Test
    void testRefresh() throws Exception {
        when(authServiceImpl.refresh((RefreshRequest) any())).thenReturn(new ResponseEntity<>(HttpStatus.CONTINUE));

        RefreshRequest refreshRequest = new RefreshRequest();
        refreshRequest.setRefreshToken("ABC123");
        String content = (new ObjectMapper()).writeValueAsString(refreshRequest);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/auth/refresh")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content);
        ResultActions actualPerformResult = MockMvcBuilders.standaloneSetup(authController)
                .build()
                .perform(requestBuilder);
        actualPerformResult.andExpect(MockMvcResultMatchers.status().is(100));
    }
}

