package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.TransmissionTypeDTO;
import com.team19.carmicroservice.model.TransmissionType;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.TransmissionTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.Valid;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/transmission", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransmissionTypeController {

    @Autowired
    private TransmissionTypeServiceImpl transmissionTypeService;

    Logger logger = LoggerFactory.getLogger(TransmissionTypeController.class);

    @GetMapping
    public ResponseEntity<List<TransmissionTypeDTO>> getAllTransmissionType() {

        List<TransmissionType> transmissionTypes = transmissionTypeService.getAllTransmissionTypes();
        List<TransmissionTypeDTO> transmissionTypeDTOS = new ArrayList<>();

        for (TransmissionType t : transmissionTypes) {
            if (!t.isRemoved()) {
                transmissionTypeDTOS.add(new TransmissionTypeDTO(t));
            }
        }

        logger.info("TT-read;"); //TT-transmission type
        return new ResponseEntity<>(transmissionTypeDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('transmission_type_create')")
    public ResponseEntity<TransmissionTypeDTO> addTransmissionType(@Valid @RequestBody TransmissionTypeDTO transmissionTypeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        TransmissionType transmissionType = transmissionTypeService.addTransmissiontype(transmissionTypeDTO);

        if (transmissionType != null) {
            logger.info(MessageFormat.format("TT-ID:{0}-created;UserID:{1}", transmissionType.getId(), cp.getUserID()));
            return new ResponseEntity<>(new TransmissionTypeDTO(transmissionType), HttpStatus.CREATED);
        }
        logger.info(MessageFormat.format("TT-not created;UserID:{0}", cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('transmission_type_delete')")
    public ResponseEntity<?> removeTransmissionType(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if (transmissionTypeService.removeTransmissionType(id)) {
            logger.info(MessageFormat.format("TT-ID:{0}-deleted;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info(MessageFormat.format("TT-ID:{0}-not found;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
