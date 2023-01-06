package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.service.api.CarService;
import com.example.transportcompanyapplication.service.api.ScheduledService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledServiceImpl implements ScheduledService {

    private final HistoryRepository historyRepository;

    private final CarService carService;

    public ScheduledServiceImpl(HistoryRepository historyRepository, CarService carService) {
        this.historyRepository = historyRepository;
        this.carService = carService;
    }

    @Override
    @Scheduled(cron = "0 0 0 * * *")
    public void performDailyAction() {
        historyRepository.clearLog();
        carService.updateAvgKilometrage();
    }
}
