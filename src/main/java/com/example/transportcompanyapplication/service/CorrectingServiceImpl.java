package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.repository.CorrectingRepository;
import com.example.transportcompanyapplication.service.api.CarService;
import com.example.transportcompanyapplication.service.api.CorrectingService;
import org.springframework.stereotype.Service;

@Service
public class CorrectingServiceImpl implements CorrectingService {

    private final CorrectingRepository correctingRepository;

    private final CarService carService;

    public CorrectingServiceImpl(CorrectingRepository correctingRepository, CarService carService) {
        this.correctingRepository = correctingRepository;
        this.carService = carService;
    }

    @Override
    public CorrectingData getCorrectingData() {
        return correctingRepository.findById(1L).orElseThrow(
                () -> new ResourceNotFoundException("Не удалось получить доступ к данным коррекции пробега!")
        );
    }

    @Override
    public CorrectingData updateCorrectingData(CorrectingData correctingData) {
        correctingRepository.update(correctingData);
        carService.correctAllCarKilometrage();
        return getCorrectingData();
    }

    @Override
    public void correctCarKilometrage(Long carId) {
        carService.correctCarKilometrage(carId);
    }

    @Override
    public void correctAllCarKilometrage() {
        carService.correctAllCarKilometrage();
    }
}
