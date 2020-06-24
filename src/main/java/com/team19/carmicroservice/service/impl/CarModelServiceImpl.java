package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.CarModelDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.repository.CarBrandRepository;
import com.team19.carmicroservice.repository.CarModelRepository;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.service.CarModelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {

    @Autowired
    private CarModelRepository carModelRepository;

    @Autowired
    private CarBrandRepository carBrandRepository;

    @Autowired
    private CarRepository carRepository;

    Logger logger = LoggerFactory.getLogger(CarModelServiceImpl.class);

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
    public boolean removeCarModel(Long id) {
        CarModel carModel = carModelRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if (c.getCarModel().equals(carModel)) {
                return false;
            }
        }
        carModel.setRemoved(true);
        carModelRepository.save(carModel);
        return true;
    }

    @Override
    public CarModel findByBrandAndModelName(String brandName, String modelName) {
        CarModel carModel = carModelRepository.findByNameAndCarBrandName(modelName,brandName);
        logger.info(MessageFormat.format("CM-ID:{0}-FBN;", carModel.getId()));//CM Car Model, FBN Find By Name
        return carModel;
    }
}
