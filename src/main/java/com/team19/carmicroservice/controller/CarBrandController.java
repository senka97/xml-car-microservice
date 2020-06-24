package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarBrandDTO;
import com.team19.carmicroservice.model.CarBrand;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.CarBrandServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarBrandController {

    @Autowired
    private CarBrandServiceImpl carBrandService;

    Logger logger = LoggerFactory.getLogger(CarBrandController.class);

    @GetMapping
    public ResponseEntity<List<CarBrandDTO>> getAllCarBrands() {

        List<CarBrand> carBrands = carBrandService.getAllCarBrands();
        List<CarBrandDTO> carBrandDTOS = new ArrayList<>();

        for (CarBrand cb : carBrands) {
            if (!cb.isRemoved()) {
                carBrandDTOS.add(new CarBrandDTO(cb));
            }
        }

        logger.info("CarB-read;"); //CarB-car brand
        return new ResponseEntity<>(carBrandDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('car_brand_create')")
    public ResponseEntity<CarBrandDTO> addCarBrand(@RequestBody CarBrandDTO carBrandDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        CarBrand carBrand = carBrandService.addCarBrand(carBrandDTO);

        if (carBrand != null) {
            logger.info(MessageFormat.format("CarB-ID:{0}-created;UserID:{1}", carBrand.getId(), cp.getUserID()));
            return new ResponseEntity<>(new CarBrandDTO(carBrand), HttpStatus.CREATED);
        }

        logger.warn(MessageFormat.format("CarB-not created;UserID:{0}", cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('car_brand_delete')")
    public ResponseEntity<?> removeCarBrand(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if (carBrandService.removeCarBrand(id)) {
            logger.info(MessageFormat.format("CarB-ID:{0}-deleted;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.warn(MessageFormat.format("CarB-ID:{0}-not found;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
