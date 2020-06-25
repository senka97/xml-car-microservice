package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.controller.CarBrandController;
import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.repository.CarBrandRepository;
import com.team19.carmicroservice.repository.CarModelRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class CarBrandServiceImpl implements CarBrandService {

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarModelRepository carModelRepository;

    Logger logger = LoggerFactory.getLogger(CarBrandServiceImpl.class);

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
            carBrandRepository.save(carBrand);
        }

        return carBrand;
    }

    @Override
    public boolean removeCarBrand(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        CarBrand carBrand = carBrandRepository.getOne(id);
        List<CarModel> carModels = carModelRepository.findAll();
        for (CarModel cm : carModels) {
            if (cm.getCarBrand().equals(carBrand)) {
                logger.warn(MessageFormat.format("CarB-ID:{0}-has relation to carM;UserID:{1}", id, cp.getUserID()));
                return false;
            }
        }
        carBrand.setRemoved(true);
        carBrandRepository.save(carBrand);
        return true;
    }

}
