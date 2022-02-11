package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarService {
    private final CarRepository repository;

    public CarService(CarRepository repository) {
        this.repository = repository;
    }

    public List<Car> findAll(){
        return repository.findAll();
    }

    public Car findById(Long id) throws ResourceNotFoundException{
        return repository.findById(id).orElseThrow(
                ()->new ResourceNotFoundException("Car with id = "+ id + "not found")
        );
    }

    public Car save(Car car){
        return repository.save(car);
    }

    public Car delete(Long id) throws ResourceNotFoundException {
        Car deletedCar = findById(id);
        repository.delete(deletedCar);
        return deletedCar;
    }

    public Car update(Car car) throws ResourceNotFoundException {
        findById(car.getId());
        return repository.save(car);
    }

    public void updateCoordinates(NewCoordinatesOfCar newCoordinates)throws ResourceNotFoundException{
        Car updatedCar = findById(newCoordinates.getCarId());
        updatedCar.setLatitude(newCoordinates.getLatitude());
        updatedCar.setLongitude(newCoordinates.getLongitude());
        repository.save(updatedCar);
    }

    public void doMaintenance(Long id) throws ResourceNotFoundException {
        Car car = findById(id);
        car.doMaintenance();
        repository.save(car);
    }
}
