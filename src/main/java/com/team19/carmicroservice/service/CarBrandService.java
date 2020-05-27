package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;

import java.util.List;

public interface CarBrandService {

    List<CarBrand> getAllCarBrands();
    CarBrand addCarBrand(CarBrandDTO carBrandDTO);
    void removeCarBrand(Long id);
}
