package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.TransmissionTypeDTO;
import com.team19.carmicroservice.model.TransmissionType;
import com.team19.carmicroservice.service.impl.TransmissionTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/transmission", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransmissionTypeController {

    @Autowired
    private TransmissionTypeServiceImpl transmissionTypeService;

    @GetMapping
    public ResponseEntity<List<TransmissionTypeDTO>> getAllTransmissionType() {

        List<TransmissionType> transmissionTypes = transmissionTypeService.getAllTransmissionTypes();
        List<TransmissionTypeDTO> transmissionTypeDTOS = new ArrayList<>();

        for (TransmissionType t : transmissionTypes) {
            if (!t.isRemoved()) {
                transmissionTypeDTOS.add(new TransmissionTypeDTO(t));
            }
        }

        return new ResponseEntity<>(transmissionTypeDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('addTransmissionType')")
    public ResponseEntity<TransmissionTypeDTO> addTransmissionType(@RequestBody TransmissionTypeDTO transmissionTypeDTO) {
        TransmissionType transmissionType = transmissionTypeService.addTransmissiontype(transmissionTypeDTO);

        if (transmissionType != null) {
            return new ResponseEntity<>(new TransmissionTypeDTO(transmissionType), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('removeTransmissionType')")
    public ResponseEntity<?> removeTransmissionType(@PathVariable Long id) {
        transmissionTypeService.removeTransmissionType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
