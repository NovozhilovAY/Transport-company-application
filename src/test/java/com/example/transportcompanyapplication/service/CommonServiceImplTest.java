package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.DriverRepository;
import com.example.transportcompanyapplication.util.EntityValidator;
import com.example.transportcompanyapplication.util.PatchMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Disabled;

import org.junit.jupiter.api.Test;
import org.springframework.validation.beanvalidation.CustomValidatorBean;

class CommonServiceImplTest {
    /**
     * Method under test: {@link CommonServiceImpl#findAll()}
     */
    @Test
    void testFindAll() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        ArrayList<Driver> driverList = new ArrayList<>();
        when(driverRepository.findAll()).thenReturn(driverList);
        List<Driver> actualFindAllResult = (new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())))).findAll();
        assertSame(driverList, actualFindAllResult);
        assertTrue(actualFindAllResult.isEmpty());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test: {@link CommonServiceImpl#findAll()}
     */
    @Test
    void testFindAll2() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findAll()).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> (new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())))).findAll());
        verify(driverRepository).findAll();
    }

    /**
     * Method under test: {@link CommonServiceImpl#findById(Object)}
     */
    @Test
    void testFindById() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.of(driver));
        assertSame(driver,
                (new DriverServiceImpl(driverRepository, new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean()))))
                        .findById(123L));
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#findById(Object)}
     */
    @Test
    void testFindById2() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> (new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())))).findById(123L));
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#findById(Object)}
     */
    @Test
    void testFindById3() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> (new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())))).findById(123L));
        verify(driverRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#save(Object)}
     */
    @Test
    void testSave() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.save((Driver) any())).thenReturn(driver);
        DriverServiceImpl driverServiceImpl = new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())));

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");
        assertSame(driver, driverServiceImpl.save(driver1));
        verify(driverRepository).save((Driver) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#save(Object)}
     */
    @Test
    void testSave2() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        when(driverRepository.save((Driver) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        DriverServiceImpl driverServiceImpl = new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())));

        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.save(driver));
        verify(driverRepository).save((Driver) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#update(Object, Object)}
     */
    @Test
    void testUpdate2() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Optional<Driver> ofResult = Optional.of(driver);
        DriverRepository driverRepository = mock(DriverRepository.class);
        doNothing().when(driverRepository).update((Driver) any());
        when(driverRepository.findById((Long) any())).thenReturn(ofResult);
        DriverServiceImpl driverServiceImpl = new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())));

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");
        assertSame(driver, driverServiceImpl.update(driver1, 123L));
        verify(driverRepository, atLeast(1)).findById((Long) any());
        verify(driverRepository).update((Driver) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#update(Object, Object)}
     */
    @Test
    void testUpdate3() {
        Driver driver = new Driver();
        driver.setDrivingLicense("Driving License");
        driver.setFirstName("Jane");
        driver.setId(123L);
        driver.setLastName("Doe");
        driver.setMiddleName("Middle Name");
        Optional<Driver> ofResult = Optional.of(driver);
        DriverRepository driverRepository = mock(DriverRepository.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(driverRepository).update((Driver) any());
        when(driverRepository.findById((Long) any())).thenReturn(ofResult);
        DriverServiceImpl driverServiceImpl = new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())));

        Driver driver1 = new Driver();
        driver1.setDrivingLicense("Driving License");
        driver1.setFirstName("Jane");
        driver1.setId(123L);
        driver1.setLastName("Doe");
        driver1.setMiddleName("Middle Name");
        assertThrows(ResourceNotFoundException.class, () -> driverServiceImpl.update(driver1, 123L));
        verify(driverRepository).findById((Long) any());
        verify(driverRepository).update((Driver) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#deleteById(Object)}
     */
    @Test
    void testDeleteById() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        doNothing().when(driverRepository).deleteById((Long) any());
        (new DriverServiceImpl(driverRepository, new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean()))))
                .deleteById(123L);
        verify(driverRepository).deleteById((Long) any());
    }

    /**
     * Method under test: {@link CommonServiceImpl#deleteById(Object)}
     */
    @Test
    void testDeleteById2() {
        DriverRepository driverRepository = mock(DriverRepository.class);
        doThrow(new ResourceNotFoundException("An error occurred")).when(driverRepository).deleteById((Long) any());
        assertThrows(ResourceNotFoundException.class, () -> (new DriverServiceImpl(driverRepository,
                new PatchMapper<>(new EntityValidator<>(new CustomValidatorBean())))).deleteById(123L));
        verify(driverRepository).deleteById((Long) any());
    }
}

