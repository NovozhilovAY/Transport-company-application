package com.example.transportcompanyapplication.controller;

import com.example.transportcompanyapplication.model.Driver;
import com.example.transportcompanyapplication.service.DriverService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/drivers")
@CrossOrigin(value = "*")
public class DriverController {

    final private DriverService service;

    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping
    public List<Driver> getAllDrivers() {
        return service.findAll();
    }

    @GetMapping("/free")
    public List<Driver> getFreeDrivers(){
        return service.getFreeDrivers();
    }

    @GetMapping("/{id}")
    public Driver getDriverById(@PathVariable(value = "id") Long id){
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Driver createDriver(@RequestBody @Valid Driver driver){
        return service.save(driver);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteDriver(@PathVariable(value = "id") Long id){
        service.deleteById(id);
    }

    @PutMapping
    public Driver updateDriver(@RequestBody @Valid Driver driver) {
        return service.update(driver, driver.getId());
    }

    @PatchMapping("/{id}")
    public Driver partialUpdate(@PathVariable Long id,@RequestBody Driver driver){
        return service.partialUpdate(driver, id);
    }
}
