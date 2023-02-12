package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.HistoryDatesRequest;
import com.example.transportcompanyapplication.dto.HistoryIntervalsRequest;
import com.example.transportcompanyapplication.dto.KmByDateIntervalRequest;
import com.example.transportcompanyapplication.dto.KmByDateRequest;

import java.util.List;

public interface HistoryService {

    List<Double> getKilometrageByDateInterval(HistoryIntervalsRequest request);

    List<Double> getKilometrageByDate(HistoryDatesRequest request);
}
