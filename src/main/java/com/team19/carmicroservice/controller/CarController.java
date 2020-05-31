package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.dto.ExistingCarDTO;
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

    @PostMapping(value = "/car",consumes = "application/json")
    public CarDTO addCar(@RequestBody CarDTO carDTO)
    {
        return this.carService.addNewCar(carDTO);
    }

    @GetMapping(value="/car/ad/notActive")
    public ArrayList<ExistingCarDTO> getCarsWithNoActiveAds()
    {
        return this.carService.getCarsWithNoActiveAds();
    }

    @GetMapping(value="/car/{brand}/{model}/{feul_type}/{class_type}/{transmission_type}/{mileage}/{children_seats}")
    public ArrayList<CarDTO> searchCars(@PathVariable("brand") String brand,@PathVariable("model") String model,
                                        @PathVariable("feul_type") String feulType,@PathVariable("class_type") String classType,
                                        @PathVariable("transmission_type") String transType,@PathVariable("mileage") int mileage,
                                        @PathVariable("children_seats") int childrenSeats)
    {
        System.out.println("Searching");
        System.out.println(brand);
        System.out.println(model);
        System.out.println(feulType);
        System.out.println(classType);
        System.out.println(transType);
        System.out.println(mileage);
        System.out.println(childrenSeats);
        return this.carService.searchCars(brand,model,feulType,classType,transType,mileage,childrenSeats);
    }
}
