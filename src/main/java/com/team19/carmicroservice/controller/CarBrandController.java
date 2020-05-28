package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.service.impl.CarBrandServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarBrandController {

    @Autowired
    private CarBrandServiceImpl carBrandService;

    @GetMapping
    public ResponseEntity<List<CarBrandDTO>> getAllCarBrands() {

        List<CarBrand> carBrands = carBrandService.getAllCarBrands();
        List<CarBrandDTO> carBrandDTOS = new ArrayList<>();

        for (CarBrand cb : carBrands) {
            if (!cb.isRemoved()) {
                carBrandDTOS.add(new CarBrandDTO(cb));
            }
        }

        return new ResponseEntity<>(carBrandDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('addCarBrand')")
    public ResponseEntity<CarBrandDTO> addCarBrand(@RequestBody CarBrandDTO carBrandDTO) {
        CarBrand carBrand = carBrandService.addCarBrand(carBrandDTO);

        if (carBrand != null) {
            return new ResponseEntity<>(new CarBrandDTO(carBrand), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('removeCarBrand')")
    public ResponseEntity<?> removeCarBrand(@PathVariable Long id) {
        carBrandService.removeCarBrand(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}