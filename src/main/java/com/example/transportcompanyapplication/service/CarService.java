package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CarService extends AbstractService<Car, Long>{

    public CarService(CarRepository repository, PatchMapper<Car> mapper) {
        super(repository,mapper);
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
        return this.save(car, id);
    }
}
