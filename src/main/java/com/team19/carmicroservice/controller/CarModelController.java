package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarModelDTO;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.CarModelServiceImpl;
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
@RequestMapping(value = "/model", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarModelController {

    @Autowired
    private CarModelServiceImpl carModelService;

    Logger logger = LoggerFactory.getLogger(CarModelController.class);

    @GetMapping(value = "/brand/{id}")
    public ResponseEntity<List<CarModelDTO>> getAllCarModelByCarBrandId(@PathVariable Long id) {
        List<CarModel> carModels = carModelService.getAllCarModelsByIdCarBrand(id);
        List<CarModelDTO> carModelDTOS = new ArrayList<>();

        for (CarModel cm : carModels) {
            if (!cm.isRemoved()) {
                carModelDTOS.add(new CarModelDTO(cm));
            }
        }
        logger.info("CarM-read;"); //CarM-car model
        return new ResponseEntity<>(carModelDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/brand/{id}")
    @PreAuthorize("hasAuthority('car_model_create')")
    public ResponseEntity<CarModelDTO> addCarModel(@Valid @RequestBody CarModelDTO carModelDTO, @PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        CarModel carModel = carModelService.addCarModel(carModelDTO, id);

        if (carModel != null) {
            logger.info(MessageFormat.format("CarM-ID:{0}-created;CarBID:{1};UserID:{2}", carModel.getId(), id, cp.getUserID()));
            return new ResponseEntity<>(new CarModelDTO(carModel), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('car_model_delete')")
    public ResponseEntity<?> removeCarModel(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if (carModelService.removeCarModel(id)) {
            logger.info(MessageFormat.format("CarM-ID:{0}-deleted;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info(MessageFormat.format("CarM-ID:{0}-not deleted;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
