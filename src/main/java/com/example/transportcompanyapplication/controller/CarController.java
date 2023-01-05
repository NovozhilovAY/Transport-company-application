package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.service.CarService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/cars")
public class CarController {
    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public List<Car> getAllCars(){
        return carService.findAll();
    }

    @GetMapping("/{id}")
    public Car getCarById(@PathVariable Long id){
        return carService.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Car createCar(@RequestBody @Valid Car car){
        return carService.save(car);
    }

    @PutMapping
    public Car updateCar(@RequestBody @Valid Car car){
        return carService.update(car,car.getId());
    }

    @PatchMapping("/coordinates/{id}")
    public Car updateCoordinates(@PathVariable Long id,@RequestBody @Valid NewCoordinatesOfCar newCoordinates){
        return carService.updateCoordinates(id, newCoordinates);
    }

    @PatchMapping("/to-1/{id}")
    public Car doTo1(@PathVariable Long id){
        return carService.doTo1(id);
    }

    @PatchMapping("/to-2/{id}")
    public Car doTo2(@PathVariable Long id){
        return carService.doTo2(id);
    }

    @PatchMapping("/kr/{id}")
    public Car doKr(@PathVariable Long id){
        return carService.doKr(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    private void deleteCarById(@PathVariable Long id){
        carService.deleteById(id);
    }


    @PatchMapping("/{id}")
    public Car partialUpdate(@PathVariable Long id, @RequestBody Car car){
        return carService.partialUpdate(car, id);
    }

    @GetMapping("/update-avg-km")
    public void updateAvgKm() {
        carService.updateAvgKilometrage();
    }

    @GetMapping("/report/next-maint-dates/{id}")
    public NextMaintDates getMaintDates(@PathVariable Long id) {
        return  carService.getNextMaintDates(id);
    }
}
