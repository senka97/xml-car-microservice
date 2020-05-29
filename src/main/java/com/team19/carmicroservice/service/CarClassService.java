package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarClass;

import java.util.List;

public interface CarClassService {

    List<CarClass> getAllCarClasses();
    CarClass addCarClass(CarClassDTO carClassDTO);
    void removeCarClass(Long id);
    CarClass findByName(String name);

}
