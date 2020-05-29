package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }

    @Override
    public CarDTO getCar(Long id) {

        CarDTO carDTO = new CarDTO();

        Car car = carRepository.findById(id).orElse(null);

            if(car != null)
            {
                carDTO.setId(car.getId());
                carDTO.setChildrenSeats(car.getChildrenSeats());
                carDTO.setRate(car.getRate());
                carDTO.setMileage(car.getMileage());
                carDTO.setHasAndroidApp(car.getHasAndroidApp());
                carDTO.setCarBrand(car.getCarModel().getCarBrand().getName());
                carDTO.setCarModel(car.getCarModel().getName());
                carDTO.setCarClass(car.getCarClass().getName());
                carDTO.setTransType(car.getTransmissionType().getName());
                carDTO.setFuelType(car.getFuelType().getName());
                //photos

            }
            else
            {
                return null;
            }

        return carDTO;
    }

    @Override
    public ArrayList<AdDTO> findCars(ArrayList<AdDTO> ads) {

        for( AdDTO ad : ads)
        {
            CarDTO carDTO = ad.getCar();

            Car car = carRepository.findById(ad.getCar().getId()).orElse(null);

            if(car != null)
            {
                carDTO.setId(car.getId());
                carDTO.setChildrenSeats(car.getChildrenSeats());
                carDTO.setRate(car.getRate());
                carDTO.setMileage(car.getMileage());
                carDTO.setHasAndroidApp(car.getHasAndroidApp());
                carDTO.setCarBrand(car.getCarModel().getCarBrand().getName());
                carDTO.setCarModel(car.getCarModel().getName());
                carDTO.setCarClass(car.getCarClass().getName());
                carDTO.setTransType(car.getTransmissionType().getName());
                carDTO.setFuelType(car.getFuelType().getName());
                //photos
            }
        }
        return ads;
    }

}
