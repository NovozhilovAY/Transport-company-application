package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class CarService{
    private final CarRepository repository;
    private final PatchMapper<Car> mapper;

    public CarService(CarRepository repository, PatchMapper<Car> mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public List<Car> findAll(){
        return repository.findAll();
    }

    public Car findById(Long id){
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Car with id = "+ id + "not found")
        );
    }

    public Car save(Car car){
        repository.save(car);
        return this.findById(car.getId());
    }

    public void delete(Long id){
        Car deletedCar = findById(id);
        repository.delete(deletedCar);
    }

    public Car update(Car car){
        findById(car.getId());
        return this.save(car);
    }

    public void updateCoordinates(Long id,NewCoordinatesOfCar newCoordinates){
        Car updatedCar = findById(id);
        updatedCar.setLatitude(newCoordinates.getLatitude());
        updatedCar.setLongitude(newCoordinates.getLongitude());
        this.save(updatedCar);
    }

    public Car doMaintenance(Long id){
        Car car = findById(id);
        car.doMaintenance();
        return this.save(car);
    }

    public Car partialUpdate(Long id, Car car) {
        Car updatedCar = findById(id);
        mapper.update(car,updatedCar);
        return this.save(updatedCar);
    }
}
