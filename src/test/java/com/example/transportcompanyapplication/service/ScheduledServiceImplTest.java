package com.example.transportcompanyapplication.service;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;

import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.service.api.CarService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ScheduledServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ScheduledServiceImplTest {
    @MockBean
    private CarService carService;

    @MockBean
    private HistoryRepository historyRepository;

    @Autowired
    private ScheduledServiceImpl scheduledServiceImpl;

    /**
     * Method under test: {@link ScheduledServiceImpl#performDailyAction()}
     */
    @Test
    void testPerformDailyAction() {
        doNothing().when(historyRepository).clearLog();
        doNothing().when(carService).updateAvgKilometrage();
        scheduledServiceImpl.performDailyAction();
        verify(historyRepository).clearLog();
        verify(carService).updateAvgKilometrage();
    }
}

