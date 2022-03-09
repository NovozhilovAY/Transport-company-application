package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.KmByDateIntervalRequest;
import com.example.transportcompanyapplication.dto.KmByDateRequest;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.service.HistoryService;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/history")
@CrossOrigin(value = "*")
public class HistoryController {
    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/date")
    public Double getKilometrageByDate(@Valid KmByDateRequest request){
        KmByDateRequest req = request;
        return service.getKilometrageByDate(request);
    }

    @GetMapping("/date-interval")
    public Double getKilometrageByDateInterval(@Valid KmByDateIntervalRequest request){
        return service.getKilometrageByDateInterval(request);
    }

}
