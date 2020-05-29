package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.FuelTypeDTO;
import com.team19.carmicroservice.model.FuelType;

import java.util.List;

public interface FuelTypeService {

    List<FuelType> getAllFuelTypes();
    FuelType addFuelType(FuelTypeDTO fuelTypeDTO);
    FuelType findByName(String name);
    boolean removeFuelType(Long id);
}
