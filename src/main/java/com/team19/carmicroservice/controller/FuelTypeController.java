package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.FuelTypeDTO;
import com.team19.carmicroservice.model.FuelType;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.FuelTypeServiceImpl;
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

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/fuel", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    private FuelTypeServiceImpl fuelTypeService;

    Logger logger = LoggerFactory.getLogger(FuelTypeController.class);

    @GetMapping
    public ResponseEntity<List<FuelTypeDTO>> getAllFuelTypes() {

        List<FuelType> fuelTypes = fuelTypeService.getAllFuelTypes();
        List<FuelTypeDTO> fuelTypeDTOS = new ArrayList<>();

        for (FuelType f : fuelTypes) {
            if (!f.isRemoved()) {
                fuelTypeDTOS.add(new FuelTypeDTO(f));
            }
        }

        logger.info("FT-read;"); //FT-fuel type
        return new ResponseEntity<>(fuelTypeDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('fuel_type_create')")
    public ResponseEntity<FuelTypeDTO> addFuelType(@RequestBody FuelTypeDTO fuelTypeDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        FuelType fuelType = fuelTypeService.addFuelType(fuelTypeDTO);

        if (fuelType != null) {
            logger.info(MessageFormat.format("FT-ID:{0}-created;UserID:{1}", fuelType.getId(), cp.getUserID()));
            return new ResponseEntity<>(new FuelTypeDTO(fuelType), HttpStatus.CREATED);
        }
        logger.info(MessageFormat.format("FT-not created;UserID:{0}", cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('fuel_type_delete')")
    public ResponseEntity<?> removeFuelType(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if (fuelTypeService.removeFuelType(id)) {
            logger.info(MessageFormat.format("FT-ID:{0}-deleted;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info(MessageFormat.format("FT-ID:{0}-not found;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
