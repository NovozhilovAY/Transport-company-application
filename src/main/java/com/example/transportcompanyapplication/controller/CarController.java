package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.dto.Response;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id) throws ResourceNotFoundException {
        return carService.findById(id);
    }

    @PostMapping
    public Car createCar(@RequestBody @Valid Car car){
        return carService.save(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody @Valid Car car) throws ResourceNotFoundException{
        return carService.update(car);
    }

    @PatchMapping
    public ResponseEntity<Response> updateCoordinates(@RequestBody @Valid NewCoordinatesOfCar newCoordinates) throws ResourceNotFoundException{
        carService.updateCoordinates(newCoordinates);
        return new ResponseEntity<>(new Response("Coordinates was updated"), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    private Car deleteCarById(@PathVariable Long id) throws ResourceNotFoundException {
        return carService.delete(id);
    }

}
