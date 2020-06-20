package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.dto.CarStatisticDTO;
import com.team19.carmicroservice.dto.ExistingCarDTO;
import com.team19.carmicroservice.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('car_write')")
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

    @PutMapping(value="/car/user/{userId}/ad/{adId}/{rate}")
    @PreAuthorize("hasAuthority('rate_update')")
    public ResponseEntity<?> rating(@PathVariable Long userId, @PathVariable Long adId, @PathVariable double rate) {
        if (carService.rating(userId, adId, rate)) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value="/car/user/mostKilometers", produces = "application/json")
    @PreAuthorize("hasAuthority('car_read')")
    public CarStatisticDTO getCarWithMostKilometers()  {
        return carService.getCarWithMostKilometers();
    }

    @GetMapping(value="/car/user/bestScore", produces = "application/json")
    @PreAuthorize("hasAuthority('car_read')")
    public CarStatisticDTO getCarWithBestScore()  {
        return carService.getCarWithBestScore();
    }

    @GetMapping(value="/car/user/mostComments", produces = "application/json")
    @PreAuthorize("hasAuthority('car_read')")
    public CarStatisticDTO getCarWithMostComments() {
        return carService.getCarWithMostComments();
    }
}
