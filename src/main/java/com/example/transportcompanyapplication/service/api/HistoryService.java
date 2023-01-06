package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.KmByDateIntervalRequest;
import com.example.transportcompanyapplication.dto.KmByDateRequest;

public interface HistoryService {

    Double getKilometrageByDate(KmByDateRequest request);

    Double getKilometrageByDateInterval(KmByDateIntervalRequest request);
}
