package com.example.transportcompanyapplication.service.api;

import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.model.Car;

public interface CarService extends CommonService<Car, Long> {

    Car updateCoordinates(Long id, NewCoordinatesOfCar newCoordinates);

    Car doTo1(Long id);

    Car doTo2(Long id);

    Car doKr(Long id);

    void correctCarKilometrage(Long id);

    void correctAllCarKilometrage();

    void updateAvgKilometrage();
}
