package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.repository.CarClassRepository;
import com.team19.carmicroservice.service.CarClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarClassServiceImpl implements CarClassService {

    @Autowired
    private CarClassRepository carClassRepository;

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
    public void removeCarClass(Long id) {
        CarClass carClass = carClassRepository.getOne(id);
        carClass.setRemoved(true);
        carClassRepository.save(carClass);
    }
}
