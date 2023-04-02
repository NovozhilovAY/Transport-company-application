package com.example.transportcompanyapplication.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.transportcompanyapplication.correcting.model.CityType;
import com.example.transportcompanyapplication.correcting.model.ClimateType;
import com.example.transportcompanyapplication.correcting.model.ReliefType;
import com.example.transportcompanyapplication.correcting.model.RoadType;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.repository.CorrectingRepository;
import com.example.transportcompanyapplication.service.api.CarService;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CorrectingServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CorrectingServiceImplTest {
    @MockBean
    private CarService carService;

    @MockBean
    private CorrectingRepository correctingRepository;

    @Autowired
    private CorrectingServiceImpl correctingServiceImpl;

    /**
     * Method under test: {@link CorrectingServiceImpl#getCorrectingData()}
     */
    @Test
    void testGetCorrectingData() {
        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        assertSame(correctingData, correctingServiceImpl.getCorrectingData());
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#getCorrectingData()}
     */
    @Test
    void testGetCorrectingData2() {
        when(correctingRepository.findById((Long) any())).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.getCorrectingData());
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#getCorrectingData()}
     */
    @Test
    void testGetCorrectingData3() {
        when(correctingRepository.findById((Long) any())).thenThrow(new ResourceNotFoundException("An error occurred"));
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.getCorrectingData());
        verify(correctingRepository).findById((Long) any());
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#updateCorrectingData(CorrectingData)}
     */
    @Test
    void testUpdateCorrectingData() {
        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(correctingRepository).update((CorrectingData) any());
        doNothing().when(carService).correctAllCarKilometrage();

        CorrectingData correctingData1 = new CorrectingData();
        correctingData1.setCityType(CityType.OUT);
        correctingData1.setClimateType(ClimateType.TEMPERATE);
        correctingData1.setId(123L);
        correctingData1.setReliefType(ReliefType.R1);
        correctingData1.setRoadType(RoadType.D1);
        assertSame(correctingData, correctingServiceImpl.updateCorrectingData(correctingData1));
        verify(correctingRepository).findById((Long) any());
        verify(correctingRepository).update((CorrectingData) any());
        verify(carService).correctAllCarKilometrage();
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#updateCorrectingData(CorrectingData)}
     */
    @Test
    void testUpdateCorrectingData2() {
        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        Optional<CorrectingData> ofResult = Optional.of(correctingData);
        when(correctingRepository.findById((Long) any())).thenReturn(ofResult);
        doNothing().when(correctingRepository).update((CorrectingData) any());
        doThrow(new ResourceNotFoundException("An error occurred")).when(carService).correctAllCarKilometrage();

        CorrectingData correctingData1 = new CorrectingData();
        correctingData1.setCityType(CityType.OUT);
        correctingData1.setClimateType(ClimateType.TEMPERATE);
        correctingData1.setId(123L);
        correctingData1.setReliefType(ReliefType.R1);
        correctingData1.setRoadType(RoadType.D1);
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.updateCorrectingData(correctingData1));
        verify(correctingRepository).update((CorrectingData) any());
        verify(carService).correctAllCarKilometrage();
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#updateCorrectingData(CorrectingData)}
     */
    @Test
    void testUpdateCorrectingData3() {
        when(correctingRepository.findById((Long) any())).thenReturn(Optional.empty());
        doNothing().when(correctingRepository).update((CorrectingData) any());
        doNothing().when(carService).correctAllCarKilometrage();

        CorrectingData correctingData = new CorrectingData();
        correctingData.setCityType(CityType.OUT);
        correctingData.setClimateType(ClimateType.TEMPERATE);
        correctingData.setId(123L);
        correctingData.setReliefType(ReliefType.R1);
        correctingData.setRoadType(RoadType.D1);
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.updateCorrectingData(correctingData));
        verify(correctingRepository).findById((Long) any());
        verify(correctingRepository).update((CorrectingData) any());
        verify(carService).correctAllCarKilometrage();
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage() {
        doNothing().when(carService).correctCarKilometrage((Long) any());
        correctingServiceImpl.correctCarKilometrage(123L);
        verify(carService).correctCarKilometrage((Long) any());
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#correctCarKilometrage(Long)}
     */
    @Test
    void testCorrectCarKilometrage2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(carService).correctCarKilometrage((Long) any());
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.correctCarKilometrage(123L));
        verify(carService).correctCarKilometrage((Long) any());
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#correctAllCarKilometrage()}
     */
    @Test
    void testCorrectAllCarKilometrage() {
        doNothing().when(carService).correctAllCarKilometrage();
        correctingServiceImpl.correctAllCarKilometrage();
        verify(carService).correctAllCarKilometrage();
    }

    /**
     * Method under test: {@link CorrectingServiceImpl#correctAllCarKilometrage()}
     */
    @Test
    void testCorrectAllCarKilometrage2() {
        doThrow(new ResourceNotFoundException("An error occurred")).when(carService).correctAllCarKilometrage();
        assertThrows(ResourceNotFoundException.class, () -> correctingServiceImpl.correctAllCarKilometrage());
        verify(carService).correctAllCarKilometrage();
    }
}

