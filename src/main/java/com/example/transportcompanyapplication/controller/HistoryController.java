package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.dto.KmByDateIntervalRequest;
import com.example.transportcompanyapplication.dto.KmByDateRequest;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.service.HistoryService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/history")
public class HistoryController {
    private final HistoryService service;

    public HistoryController(HistoryService service) {
        this.service = service;
    }

    @GetMapping("/date")
    public Double getKilometrageByDate(KmByDateRequest request) throws ResourceNotFoundException {
        return service.getKilometrageByDate(request);
    }

    @GetMapping("/date-interval")
    public Double getKilometrageByDateInterval(KmByDateIntervalRequest request) throws ResourceNotFoundException {
        return service.getKilometrageByDateInterval(request);
    }

}
