package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.repository.CarBrandRepository;
import com.team19.carmicroservice.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Override
    public List<CarBrand> getAllCarBrands() {
        return carBrandRepository.findAll();
    }

    @Override
    public CarBrand addCarBrand(CarBrandDTO carBrandDTO) {
        CarBrand carBrand = new CarBrand();

        if (carBrand != null) {
            carBrand.setName(carBrandDTO.getName());
            carBrand.setRemoved(false);
        }

        carBrandRepository.save(carBrand);
        return carBrand;
    }

    @Override
    public void removeCarBrand(Long id) {
        CarBrand carBrand = carBrandRepository.getOne(id);
        carBrand.setRemoved(true);
        carBrandRepository.save(carBrand);
    }
}
