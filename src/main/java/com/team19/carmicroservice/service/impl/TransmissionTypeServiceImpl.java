package com.team19.carmicroservice.service.impl;

import com.team19.carmicroservice.dto.TransmissionTypeDTO;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.TransmissionType;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.repository.TransmissionTypeRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.TransmissionTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.List;

@Service
public class TransmissionTypeServiceImpl implements TransmissionTypeService {

    @Autowired
    private TransmissionTypeRepository transmissionTypeRepository;

    @Autowired
    private CarRepository carRepository;

    Logger logger = LoggerFactory.getLogger(TransmissionTypeServiceImpl.class);

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
            transmissionTypeRepository.save(transmissionType);
        }
        return transmissionType;
    }

    @Override
    public boolean removeTransmissionType(Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        TransmissionType transmissionType = transmissionTypeRepository.getOne(id);
        List<Car> cars = carRepository.findAll();
        for (Car c : cars) {
            if(c.getTransmissionType().equals(transmissionType)) {
                logger.warn(MessageFormat.format("TT-ID:{0}-has relation to car;UserID:{1}", id, cp.getUserID()));
                return false;
            }
        }
        transmissionType.setRemoved(true);
        transmissionTypeRepository.save(transmissionType);
        return true;
    }

    @Override
    public TransmissionType findByName(String name) {
        TransmissionType transmissionType = transmissionTypeRepository.findByName(name);
        logger.info(MessageFormat.format("TT-ID:{0}-FBN;", transmissionType.getId()));//TT TransmissionType, FBN Find By Name
        return transmissionType;
    }
}
