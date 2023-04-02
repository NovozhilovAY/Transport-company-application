package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import com.example.transportcompanyapplication.util.PatchMapper;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {DriverServiceImpl.class})
@ExtendWith(SpringExtension.class)
class DriverServiceImplTest {
    @MockBean
    private DriverRepository driverRepository;

    @Autowired
    private DriverServiceImpl driverServiceImpl;

    @MockBean
    private PatchMapper<Driver> patchMapper;

    /**
     * Method under test: {@link DriverServiceImpl#getFreeDrivers()}
     */
    @Test
    void testGetFreeDrivers() {
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.getFreeDrivers()).thenReturn(driverList);
        List<Driver> actualFreeDrivers = driverServiceImpl.getFreeDrivers();
        assertSame(driverList, actualFreeDrivers);
        assertTrue(actualFreeDrivers.isEmpty());
        verify(driverRepository).getFreeDrivers();
    }
}

