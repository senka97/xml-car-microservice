package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.repository.CarBrandRepository;
import com.team19.carmicroservice.repository.CarModelRepository;
import com.team19.carmicroservice.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;

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
    public boolean removeCarBrand(Long id) {
        CarBrand carBrand = carBrandRepository.getOne(id);
        List<CarModel> carModels = carModelRepository.findAll();
        for (CarModel cm : carModels) {
            if (cm.getCarBrand().equals(carBrand)) {
                return false;
            }
        }
        carBrand.setRemoved(true);
        carBrandRepository.save(carBrand);
        return true;
    }

}
