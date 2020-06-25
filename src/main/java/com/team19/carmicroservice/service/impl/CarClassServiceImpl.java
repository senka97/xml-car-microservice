package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.Car;

import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.repository.CarClassRepository;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.CarClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
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

    Logger logger = LoggerFactory.getLogger(CarClassServiceImpl.class);

    @Override
    public CarClass addCarClass(CarClassDTO carClassDTO) {
        CarClass carClass = new CarClass();

        if (carClass != null) {
            carClass.setName(carClassDTO.getName());
            carClass.setRemoved(false);
            carClassRepository.save(carClass);
        }

        return carClass;
    }

    @Override
    public boolean removeCarClass(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        CarClass carClass = carClassRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if (c.getCarClass().equals(carClass)) {
                logger.warn(MessageFormat.format("CarC-ID:{0}-has relation to car;UserID:{1}", id, cp.getUserID()));
                return false;
            }
        }
        carClass.setRemoved(true);
        carClassRepository.save(carClass);
        return true;
    }

    @Override
    public CarClass findByName(String name) {
        CarClass carClass = carClassRepository.findByName(name);
        logger.info(MessageFormat.format("CC-ID:{0}-FBN;", carClass.getId()));//CC Car Class FBN Find By Name
        return carClass;
    }
}
