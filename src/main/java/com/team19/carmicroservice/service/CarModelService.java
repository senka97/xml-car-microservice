package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.CarModelDTO;
import com.team19.carmicroservice.model.CarModel;

import java.util.List;

public interface CarModelService {

    List<CarModel> getAllCarModelsByIdCarBrand(Long idCarBrand);
    CarModel addCarModel(CarModelDTO carModelDTO, Long idCarBrand);
    CarModel findByBrandAndModelName(String modelName, String brandName);
    boolean removeCarModel(Long id);

}
