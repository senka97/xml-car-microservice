package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.Car;

import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.repository.CarClassRepository;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<CarClass> getAllCarClasses() {
        return carClassRepository.findAll();
    }

    @Override
    public CarClass addCarClass(CarClassDTO carClassDTO) {
        CarClass carClass = new CarClass();

        if (carClass != null) {
            carClass.setName(carClassDTO.getName());
            carClass.setRemoved(false);
        }

        carClassRepository.save(carClass);
        return carClass;
    }

    @Override
    public boolean removeCarClass(Long id) {
        CarClass carClass = carClassRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if (c.getCarClass().equals(carClass)) {
                return false;
            }
        }
        carClass.setRemoved(true);
        carClassRepository.save(carClass);
        return true;
    }

    @Override
    public CarClass findByName(String name) {
        return carClassRepository.findByName(name);
    }
}
