package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarModelDTO;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.service.impl.CarModelServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/model", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarModelController {

    @Autowired
    private CarModelServiceImpl carModelService;

    @GetMapping(value = "/brand/{id}")
    public ResponseEntity<List<CarModelDTO>> getAllCarModelByCarBrandId(@PathVariable Long id) {
        List<CarModel> carModels = carModelService.getAllCarModelsByIdCarBrand(id);
        List<CarModelDTO> carModelDTOS = new ArrayList<>();

        for (CarModel cm : carModels) {
            if (!cm.isRemoved()) {
                carModelDTOS.add(new CarModelDTO(cm));
            }
        }
        return new ResponseEntity<>(carModelDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/brand/{id}")
    @PreAuthorize("hasAuthority('addCarModel')")
    public ResponseEntity<CarModelDTO> addCarModel(@RequestBody CarModelDTO carModelDTO, @PathVariable Long id) {
        CarModel carModel = carModelService.addCarModel(carModelDTO, id);

        if (carModel != null) {
            return new ResponseEntity<>(new CarModelDTO(carModel), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('removeCarModel')")
    public ResponseEntity<?> removeCarModel(@PathVariable Long id) {

        if (carModelService.removeCarModel(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
