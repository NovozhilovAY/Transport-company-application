package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.correcting.model.CategoryOfExploitationType;
import com.example.transportcompanyapplication.correcting.utils.CategoryOfExploitation;
import com.example.transportcompanyapplication.correcting.utils.CoeffK1;
import com.example.transportcompanyapplication.correcting.utils.CoeffK3;
import com.example.transportcompanyapplication.dto.NewCoordinatesOfCar;
import com.example.transportcompanyapplication.dto.NextMaintDates;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.CorrectingData;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.CorrectingRepository;
import com.example.transportcompanyapplication.repository.HistoryRepository;
import com.example.transportcompanyapplication.util.PatchMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class CarService extends AbstractService<Car, Long> {

    private CorrectingRepository correctingRepository;
    private HistoryRepository historyRepository;

    public CarService(CarRepository repository, PatchMapper<Car> mapper, CorrectingRepository correctingRepository, HistoryRepository historyRepository) {
        super(repository,mapper);
        this.correctingRepository = correctingRepository;
        this.historyRepository = historyRepository;
    }

    @Override
    public Car save(Car car) {
        correctCarKilometrage(car);
        car.setDefaultKmBefore();
        return super.save(car);
    }

    public Car updateCoordinates(Long id, NewCoordinatesOfCar newCoordinates) {
        Car updatedCar = findById(id);
        updatedCar.setLatitude(newCoordinates.getLatitude());
        updatedCar.setLongitude(newCoordinates.getLongitude());
        repository.update(updatedCar);
        return this.findById(id);
    }

    public Car doTo1(Long id) {
        Car car = findById(id);
        car.doTo1();
        return save(car);
    }

    public Car doTo2(Long id) {
        Car car = findById(id);
        car.doTo2();
        return save(car);
    }

    public Car doKr(Long id) {
        Car car = findById(id);
        car.doKr();
        return save(car);
    }

    public CorrectingData getCorrectingData() {
        return correctingRepository.findById(1L).orElseThrow(
                () -> new ResourceNotFoundException("Не удалось получить доступ к данным коррекции пробега!")
        );
    }

    public CorrectingData updateCorrectingData(CorrectingData correctingData) {
        correctingRepository.update(correctingData);
        correctAllCarKilometrage();
        return getCorrectingData();
    }

    public void correctCarKilometrage(Long id) {
        Car carToCorrect = repository.getById(id);
        correctCarKilometrage(carToCorrect);
        save(carToCorrect);
    }

    public void correctAllCarKilometrage() {
        List<Car> allCars = repository.findAll();
        for(Car car : allCars) {
            this.correctCarKilometrage(car.getId());
        }
    }

    public void updateAvgKilometrage() {
        List<Car> allCars = repository.findAll();
        for (Car car : allCars) {
            this.updateAvgKilometrage(car);
            repository.update(car);
        }
    }

    public void updateAvgKilometrage(Car car) {
        Integer numOfDays = historyRepository.getNumOfDays(car.getId());
        Double allKilometrage = historyRepository.getAllKilometrage(car.getId());
        if (numOfDays.equals(0)) {
            car.setAvgKilometrage(null);
        } else {
            car.setAvgKilometrage(allKilometrage / numOfDays);
        }
    }

    public NextMaintDates getNextMaintDates(Long carId) {
        Car car = findById(carId);
        String to1Date = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeTo1()));
        String to2Date = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeTo2()));
        String krDate = getDateString(getNumOfDaysBeforeMaint(car.getAvgKilometrage(), car.getKmBeforeKr()));
        return new NextMaintDates(to1Date, to2Date, krDate);
    }

    private String getDateString(Integer numOfDays) {
        if (numOfDays == null) {
            return null;
        }
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return LocalDate.now().plusDays(numOfDays).format(dateFormat);
    }

    private Integer getNumOfDaysBeforeMaint(Double avgKilometrage, Double kmBeforeMaint) {
        if (avgKilometrage == null) {
            return null;
        } else if (kmBeforeMaint <= 0.0) {
            return 0;
        }
        double numOfDays = kmBeforeMaint / avgKilometrage;
        return (int) numOfDays;
    }

    private void correctCarKilometrage(Car car) {
        CorrectingData correctingData = getCorrectingData();
        CategoryOfExploitationType categoryOfExploitationType = CategoryOfExploitation.getCategoryOfExploitation(correctingData.getReliefType(),
                correctingData.getRoadType(), correctingData.getCityType());
        Double K1TO = CoeffK1.getCoeffK1TO(categoryOfExploitationType);
        Double K1KR = CoeffK1.getCoeffK1KR(categoryOfExploitationType);
        Double K3TO = CoeffK3.getCoeffK3TO(correctingData.getClimateType());
        Double K3KR = CoeffK3.getCoeffK3KR(correctingData.getClimateType());
        car.correctKilometrage(K1TO, K3TO, K1KR, K3KR);
    }

}
