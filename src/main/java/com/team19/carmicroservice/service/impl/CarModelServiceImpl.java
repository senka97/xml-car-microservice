package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarModelDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.repository.CarBrandRepository;
import com.team19.carmicroservice.repository.CarModelRepository;
import com.team19.carmicroservice.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public List<CarModel> getAllCarModelsByIdCarBrand(Long idCarBrand) {
        return carModelRepository.findByCarBrand_Id(idCarBrand);
    }

    @Override
    public CarModel addCarModel(CarModelDTO carModelDTO, Long idCarBrand) {
        CarBrand carBrand = carBrandRepository.getOne(idCarBrand);
        CarModel carModel = new CarModel();

        if (carModel != null && carBrand != null) {
            carModel.setName(carModelDTO.getName());
            carModel.setCarBrand(carBrand);
            carModel.setRemoved(false);
        }

        carModelRepository.save(carModel);
        return carModel;
    }

    @Override
    public void removeCarModel(Long id) {
        CarModel carModel = carModelRepository.getOne(id);
        carModel.setRemoved(true);
        carModelRepository.save(carModel);
    }

    @Override
    public CarModel findByBrandAndModelName(String brandName, String modelName) {
        return carModelRepository.findByNameAndCarBrandName(modelName,brandName);
    }
}
