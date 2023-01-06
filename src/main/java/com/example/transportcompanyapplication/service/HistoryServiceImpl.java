package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.KmByDateIntervalRequest;
import com.example.transportcompanyapplication.dto.KmByDateRequest;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.service.api.HistoryService;
import com.example.transportcompanyapplication.util.DateComparator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.sql.Date;

@Service
public class HistoryServiceImpl implements HistoryService {
    private final HistoryRepository historyRepository;
    private final CarRepository carRepository;

    public HistoryServiceImpl(HistoryRepository historyRepository, CarRepository carRepository) {
        this.historyRepository = historyRepository;
        this.carRepository = carRepository;
    }

    @Override
    public Double getKilometrageByDate(KmByDateRequest request){
        this.checkCarId(request.getCarId());
        Date currentDate = new Date(System.currentTimeMillis());
        if(DateComparator.equal(currentDate, request.getDate())){
            return historyRepository.getKmForToday(request.getCarId());
        }else {
            return historyRepository.getKmByDate(request.getCarId(),request.getDate());
        }
    }

    @Override
    public Double getKilometrageByDateInterval(KmByDateIntervalRequest request){
        this.checkCarId(request.getCarId());
        Date currentDate = new Date(System.currentTimeMillis());
        Double result = historyRepository.getKmByDateInterval(request.getCarId(), request.getDate(),request.getDate2());
        if(DateComparator.equal(currentDate,request.getDate2())){
            return result + historyRepository.getKmForToday(request.getCarId());
        }else {
            return result;
        }
    }

    private void checkCarId(Long carId){
        carRepository.findById(carId).orElseThrow(
                ()->new ResourceNotFoundException("Car with id = " + carId + " not found!")
        );
    }
}
