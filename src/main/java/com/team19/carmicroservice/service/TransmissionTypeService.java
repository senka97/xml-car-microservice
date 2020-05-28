package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.TransmissionTypeDTO;
import com.team19.carmicroservice.model.TransmissionType;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface TransmissionTypeService {

    List<TransmissionType> getAllTransmissionTypes();
    TransmissionType addTransmissiontype(TransmissionTypeDTO transmissionTypeDTO);
    boolean removeTransmissionType(@PathVariable Long id);
}
