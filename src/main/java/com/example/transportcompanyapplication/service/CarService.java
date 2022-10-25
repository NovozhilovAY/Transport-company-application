package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.correcting.model.CategoryOfExploitationType;
import com.example.transportcompanyapplication.correcting.utils.CategoryOfExploitation;
import com.example.transportcompanyapplication.correcting.utils.CoeffK1;
import com.example.transportcompanyapplication.correcting.utils.CoeffK3;
import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.CorrectingRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService extends AbstractService<Car, Long>{

    private CorrectingRepository correctingRepository;
    public CarService(CarRepository repository, PatchMapper<Car> mapper, CorrectingRepository correctingRepository) {
        super(repository,mapper);
        this.correctingRepository = correctingRepository;
    }

    public Car updateCoordinates(Long id,NewCoordinatesOfCar newCoordinates){
        Car updatedCar = findById(id);
        updatedCar.setLatitude(newCoordinates.getLatitude());
        updatedCar.setLongitude(newCoordinates.getLongitude());
        repository.update(updatedCar);
        return this.findById(id);
    }

    public Car doMaintenance(Long id){
        Car car = findById(id);
        car.doMaintenance();
        return save(car);
    }

    public CorrectingData getCorrectingData(){
        return correctingRepository.findById(1L).orElseThrow(() -> new ResourceNotFoundException("Не удалось получить доступ к данным коррекции пробега!"));
    }

    public CorrectingData updateCorrectingData(CorrectingData correctingData){
        return correctingRepository.save(correctingData);
    }

    public void correctCarKilometrage(Long id){
        Car carToCorrect = repository.getById(id);
        CorrectingData correctingData = getCorrectingData();
        CategoryOfExploitationType categoryOfExploitationType = CategoryOfExploitation.getCategoryOfExploitation(correctingData.getReliefType(),
                correctingData.getRoadType(), correctingData.getCityType());
        Double K1TO = CoeffK1.getCoeffK1TO(categoryOfExploitationType);
        Double K1KR = CoeffK1.getCoeffK1KR(categoryOfExploitationType);
        Double K3TO = CoeffK3.getCoeffK3TO(correctingData.getClimateType());
        Double K3KR = CoeffK3.getCoeffK3KR(correctingData.getClimateType());
    }

    public void correctAllCarKilometrage(){
        List<Car> allCars = repository.findAll();
        for(Car car : allCars) {
            this.correctCarKilometrage(car.getId());
        }
    }
}
