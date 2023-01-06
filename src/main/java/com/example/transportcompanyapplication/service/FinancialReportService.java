package com.example.transportcompanyapplication.service;

import com.example.transportcompanyapplication.dto.CarFinancialReport;
import com.example.transportcompanyapplication.dto.FinancialReport;
import com.example.transportcompanyapplication.exceptions.ResourceNotFoundException;
import com.example.transportcompanyapplication.model.Car;
import com.example.transportcompanyapplication.model.FinancialReportData;
import com.example.transportcompanyapplication.repository.CarRepository;
import com.example.transportcompanyapplication.repository.FinancialReportRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class FinancialReportService {
    private final FinancialReportRepository repository;

    private final CarRepository carRepository;

    public FinancialReportService(FinancialReportRepository repository, CarRepository carRepository) {
        this.repository = repository;
        this.carRepository = carRepository;
    }

    public FinancialReportData getFinancialReportData() {
        return repository.findById(1L).orElseThrow(
                () -> new ResourceNotFoundException("Не удалось получить доступ к данным финансового отчета!")
        );
    }

    public FinancialReportData updateReportData(FinancialReportData financialReportData) {
        repository.update(financialReportData);
        return getFinancialReportData();
    }

    public FinancialReport getFinancialReport() {
        List<Car> allCars = carRepository.findAll();
        FinancialReport financialReport = new FinancialReport();
        List<CarFinancialReport> carFinancialReports = new ArrayList<>();
        for (Car car : allCars) {
            carFinancialReports.add(getCarFinancialReportFromCar(car));
        }
        financialReport.setCarFinancialReports(carFinancialReports);
        setTotalFields(financialReport);
        return financialReport;
    }

    private void setTotalFields(FinancialReport financialReport) {
        List<CarFinancialReport> carFinancialReports = financialReport.getCarFinancialReports();

        Integer totalNumOfTo1 = carFinancialReports.stream()
                .map(CarFinancialReport::getNumOfTo1)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalNumOfTo2 = carFinancialReports.stream()
                .map(CarFinancialReport::getNumOfTo2)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalNumOfKr = carFinancialReports.stream()
                .map(CarFinancialReport::getNumOfKr)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalCostTo1 = carFinancialReports.stream()
                .map(CarFinancialReport::getCostOfTo1)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalCostTo2 = carFinancialReports.stream()
                .map(CarFinancialReport::getCostOfTo2)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Integer totalCostKr = carFinancialReports.stream()
                .map(CarFinancialReport::getCostOfKr)
                .filter(Objects::nonNull)
                .reduce(Integer::sum)
                .orElse(0);

        Double yearKilometrage = carFinancialReports.stream()
                .map(CarFinancialReport::getYearKilometrage)
                .filter(Objects::nonNull)
                .reduce(Double::sum)
                .orElse(0.0);

        Integer totalCosts = totalCostTo1 + totalCostTo2 + totalCostKr;
        financialReport.setTotalNumOfTo1(totalNumOfTo1);
        financialReport.setTotalNumOfTo2(totalNumOfTo2);
        financialReport.setTotalNumOfKr(totalNumOfKr);
        financialReport.setTotalCostTo1(totalCostTo1);
        financialReport.setTotalCostTo2(totalCostTo2);
        financialReport.setTotalCostKr(totalCostKr);
        financialReport.setTotalKilometrage(yearKilometrage);
        financialReport.setTotalCosts(totalCosts);
    }

    private CarFinancialReport getCarFinancialReportFromCar(Car car) {
        CarFinancialReport carFinancialReport = new CarFinancialReport(car);
        if (carFinancialReport.getAvgKilometrage() == null) {
            return carFinancialReport;
        }
        FinancialReportData financialReportData = getFinancialReportData();
        Double yearKilometrage = financialReportData.getNumWorkingDays() * car.getAvgKilometrage();
        int numOfTo1 = (int) (yearKilometrage / car.getFactTo1());
        int numOfTo2 = (int) (yearKilometrage / car.getFactTo2());
        int numOfKr = (int) (yearKilometrage / car.getFactKr());
        int costOfTo1 = numOfTo1 * financialReportData.getTo1Cost();
        int costOfTo2 = numOfTo2 * financialReportData.getTo2Cost();
        int costOfKr = numOfKr * financialReportData.getKrCost();
        carFinancialReport.setNumOfTo1(numOfTo1);
        carFinancialReport.setNumOfTo2(numOfTo2);
        carFinancialReport.setNumOfKr(numOfKr);
        carFinancialReport.setCostOfTo1(costOfTo1);
        carFinancialReport.setCostOfTo2(costOfTo2);
        carFinancialReport.setCostOfKr(costOfKr);
        carFinancialReport.setYearKilometrage(yearKilometrage);
        return carFinancialReport;
    }

}
