package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.service.api.CorrectingService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/correcting")
@CrossOrigin(value = "*")
public class CorrectingController {

    private CorrectingService correctingService;

    public CorrectingController(CorrectingService correctingService) {
        this.correctingService = correctingService;
    }

    @GetMapping
    public CorrectingData getCorrectingData(){
        return correctingService.getCorrectingData();
    }

    @PostMapping
    public CorrectingData setCorrectingData(@RequestBody CorrectingData correctingData){
        return correctingService.updateCorrectingData(correctingData);
    }

    @PostMapping("/car/{id}")
    public void correctCarKilometrage(@PathVariable Long id) {
        correctingService.correctCarKilometrage(id);
    }

    @PostMapping("/cars")
    public void correctAllCarsKilometrage() {
        correctingService.correctAllCarKilometrage();
    }
}
