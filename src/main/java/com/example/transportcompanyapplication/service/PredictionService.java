package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.repository.HistoryRepository;
import org.springframework.stereotype.Service;

@Service
public class PredictionService {
    private HistoryRepository historyRepository;

    public PredictionService(HistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    public Double getAvgDailyKilometrage(Long carId){
        Integer days = historyRepository.getNumOfWorkedDays(carId);
        Double totalKilometrage = historyRepository.getTotalKilometrage(carId);
        if(days == null || totalKilometrage == null){
            return null;
        }
        return  totalKilometrage / days.doubleValue();
    }
}
