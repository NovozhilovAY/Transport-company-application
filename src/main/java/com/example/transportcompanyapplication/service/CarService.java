package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.Map;

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

    @Override
    public Car partialUpdate(Map<String, Object> source, Long id) {
        if(source.containsKey("driver") && source.get("driver") != null){
            source.put("driver", new ObjectMapper().convertValue(source.get("driver"), Driver.class));
        }
        return super.partialUpdate(source, id);
    }

    public Car doMaintenance(Long id){
        Car car = findById(id);
        car.doMaintenance();
        return update(car, id);
    }
}
