package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.FuelTypeDTO;
import com.team19.carmicroservice.model.FuelType;
import com.team19.carmicroservice.repository.FuelTypeRepository;
import com.team19.carmicroservice.service.FuelTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelTypeServiceImpl implements FuelTypeService {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

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
        }

        fuelTypeRepository.save(fuelType);
        return fuelType;
    }

    @Override
    public void removeFuelType(Long id) {
        FuelType fuelType = fuelTypeRepository.getOne(id);
        fuelType.setRemoved(true);
        fuelTypeRepository.save(fuelType);
    }
}
