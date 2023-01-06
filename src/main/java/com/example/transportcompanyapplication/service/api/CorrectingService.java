package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.model.CorrectingData;

public interface CorrectingService {

    CorrectingData getCorrectingData();

    CorrectingData updateCorrectingData(CorrectingData correctingData);

    void correctCarKilometrage(Long carId);

    void correctAllCarKilometrage();
}
