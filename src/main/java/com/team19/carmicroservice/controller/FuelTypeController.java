package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.FuelTypeDTO;
import com.team19.carmicroservice.model.FuelType;
import com.team19.carmicroservice.service.impl.FuelTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/fuel", produces = MediaType.APPLICATION_JSON_VALUE)
public class FuelTypeController {

    @Autowired
    private FuelTypeServiceImpl fuelTypeService;

    @GetMapping
    public ResponseEntity<List<FuelTypeDTO>> getAllFuelTypes() {

        List<FuelType> fuelTypes = fuelTypeService.getAllFuelTypes();
        List<FuelTypeDTO> fuelTypeDTOS = new ArrayList<>();

        for (FuelType f : fuelTypes) {
            if (!f.isRemoved()) {
                fuelTypeDTOS.add(new FuelTypeDTO(f));
            }
        }

        return new ResponseEntity<>(fuelTypeDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('addFuelType')")
    public ResponseEntity<FuelTypeDTO> addFuelType(@RequestBody FuelTypeDTO fuelTypeDTO) {
        FuelType fuelType = fuelTypeService.addFuelType(fuelTypeDTO);

        if (fuelType != null) {
            return new ResponseEntity<>(new FuelTypeDTO(fuelType), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('removeFuelType')")
    public ResponseEntity<?> removeFuelType(@PathVariable Long id) {
        fuelTypeService.removeFuelType(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
