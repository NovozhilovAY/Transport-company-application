package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.DateInterval;
import com.example.transportcompanyapplication.dto.HistoryDatesRequest;
import com.example.transportcompanyapplication.dto.HistoryIntervalsRequest;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.service.api.HistoryService;
import com.example.transportcompanyapplication.util.DateComparator;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final CarRepository carRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository, CarRepository carRepository) {
        this.historyRepository = historyRepository;
        this.carRepository = carRepository;
    }

    public List<Double> getKilometrageByDate(HistoryDatesRequest request){
        this.checkCarId(request.getId());
        Date currentDate = new Date(System.currentTimeMillis());
        List<Double> result = new ArrayList<>();
        for(Date date: request.getDates()){
            if(DateComparator.equal(currentDate, date)){
                result.add(historyRepository.getKmForToday(request.getId()));
            }else {
                result.add(historyRepository.getKmByDate(request.getId(),date));
            }
        }
        return result;
    }

    public List<Double> getKilometrageByDateInterval(HistoryIntervalsRequest request){
        this.checkCarId(request.getId());
        Date currentDate = new Date(System.currentTimeMillis());
        List<Double> result = new ArrayList<>();
        for (DateInterval interval : request.getDateIntervals()){
            if(DateComparator.intervalIncludesDate(interval, currentDate)){
                result.add(historyRepository.getKmByDateInterval(request.getId(), interval.getFrom(), interval.getTo())
                            + historyRepository.getKmForToday(request.getId()));
            }else {
                result.add(historyRepository.getKmByDateInterval(request.getId(), interval.getFrom(), interval.getTo()));
            }
        }
        return result;
    }

    private void checkCarId(Long carId){
        carRepository.findById(carId).orElseThrow(
                ()->new ResourceNotFoundException("Car with id = " + carId + " not found!")
        );
    }
}
