package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping(value="/cars/{id}", produces = "application/json")
    public CarDTO getCar(@PathVariable("id") Long carId)
    {
        return this.carService.getCar(carId);
    }

    @PostMapping(value = "cars/findCars", produces = "application/json", consumes = "application/json")
    public ArrayList<AdDTO> findCars(@RequestBody ArrayList<AdDTO> ads)
    {
        return this.carService.findCars(ads);
    }

}
