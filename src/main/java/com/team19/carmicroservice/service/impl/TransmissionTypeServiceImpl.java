package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.TransmissionTypeDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.TransmissionType;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.repository.TransmissionTypeRepository;
import com.team19.carmicroservice.service.TransmissionTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private CarRepository carRepository;

    @Override
    public List<TransmissionType> getAllTransmissionTypes() {
        return transmissionTypeRepository.findAll();
    }

    @Override
    public TransmissionType addTransmissiontype(TransmissionTypeDTO transmissionTypeDTO) {
        TransmissionType transmissionType = new TransmissionType();

        if (transmissionType != null) {
            transmissionType.setName(transmissionTypeDTO.getName());
            transmissionType.setRemoved(false);
        }

        transmissionTypeRepository.save(transmissionType);
        return transmissionType;
    }

    @Override
    public boolean removeTransmissionType(Long id) {
        TransmissionType transmissionType = transmissionTypeRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if(c.getTransmissionType().equals(transmissionType)) {
                return false;
            }
        }
        transmissionType.setRemoved(true);
        transmissionTypeRepository.save(transmissionType);
        return true;
    }

    @Override
    public TransmissionType findByName(String name) {
        return transmissionTypeRepository.findByName(name);
    }
}
