package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/api")
public class CarController {

    @Autowired
    private CarServiceImpl carService;

    @GetMapping(value="/cars/{id}")
    public CarDTO getCar(@PathVariable("id") Long carId)
    {
        System.out.println("Usao u getCar");

        return this.carService.getCar(carId);
    }

    @GetMapping(value="/cars/hello")
    public String getGello()
    {
        return "HELLO";
    }
}
