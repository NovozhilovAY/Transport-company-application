package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.HistoryDatesRequest;
import com.example.transportcompanyapplication.dto.HistoryIntervalsRequest;
import com.example.transportcompanyapplication.service.api.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/history")
@CrossOrigin(value = "*")
public class HistoryController {
    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @PostMapping("/date")
    public List<Double> getKilometrageByDate(@RequestBody HistoryDatesRequest request){
        return service.getKilometrageByDate(request);
    }

    @PostMapping("/date-interval")
    public List<Double> getKilometrageByDateInterval(@RequestBody HistoryIntervalsRequest request){
        return service.getKilometrageByDateInterval(request);
    }

}
