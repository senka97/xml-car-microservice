package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.dto.CarStatisticDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.dto.ExistingCarDTO;

import java.util.ArrayList;
import java.util.List;

public interface CarService {

    CarDTO getCar(Long carId);
    ArrayList<AdDTO> findCars(ArrayList<AdDTO> ads);
    Car getCarById(Long id);
    CarDTO addNewCar(CarDTO carDTO);
    ArrayList<ExistingCarDTO> getCarsWithNoActiveAds();
    ArrayList<CarDTO> searchCars(String brand,String model,String feulType,String classType,String transType, int mileage,int childrenSeats);
    boolean rating(Long userId, Long adId, double rate);
    CarStatisticDTO getCarWithMostComments();
    CarStatisticDTO getCarWithMostKilometers();
    CarStatisticDTO getCarWithBestScore();

}
