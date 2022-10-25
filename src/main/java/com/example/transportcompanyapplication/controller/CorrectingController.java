package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.service.CarService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correcting")
public class CorrectingController {

    private CarService carService;

    public CorrectingController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping
    public CorrectingData getCorrectingData(){
        return carService.getCorrectingData();
    }

    @PostMapping
    public CorrectingData setCorrectingData(@RequestBody CorrectingData correctingData){
        return carService.updateCorrectingData(correctingData);
    }

    @PostMapping("/car/{id}")
    public void correctCarKilometrage(@PathVariable Long id) {
        carService.correctCarKilometrage(id);
    }

    @PostMapping("/cars")
    public void correctAllCarsKilometrage() {
        carService.correctAllCarKilometrage();
    }
}
