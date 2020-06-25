package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.FuelTypeDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.FuelType;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.repository.FuelTypeRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.FuelTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @Autowired
    private CarRepository carRepository;

    Logger logger = LoggerFactory.getLogger(FuelTypeServiceImpl.class);

    @Override
    public List<FuelType> getAllFuelTypes() {
        return fuelTypeRepository.findAll();
    }

    @Override
    public FuelType addFuelType(FuelTypeDTO fuelTypeDTO) {
        FuelType fuelType = new FuelType();

        if (fuelType != null) {
            fuelType.setName(fuelTypeDTO.getName());
            fuelType.setRemoved(false);
            fuelTypeRepository.save(fuelType);
        }
        return fuelType;
    }

    @Override
    public boolean removeFuelType(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        FuelType fuelType = fuelTypeRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if (c.getFuelType().equals(fuelType)) {
                logger.warn(MessageFormat.format("FT-ID:{0}-has relation to car;UserID:{1}", id, cp.getUserID()));
                return false;
            }
        }
        fuelType.setRemoved(true);
        fuelTypeRepository.save(fuelType);
        return true;
    }

    @Override
    public FuelType findByName(String name) {
        FuelType fuelType = fuelTypeRepository.findByName(name);
        logger.info(MessageFormat.format("FT-ID:{0}-FBN;", fuelType.getId()));//FT Fuel Type, FBN Find By Name
        return fuelType;
    }

}
