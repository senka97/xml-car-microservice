package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.CarClassServiceImpl;
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
@RequestMapping(value = "/class", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarClassController {

    @Autowired
    private CarClassServiceImpl carClassService;

    Logger logger = LoggerFactory.getLogger(CarClassController.class);

    @GetMapping
    public ResponseEntity<List<CarClassDTO>> getAllCarClasses() {

        List<CarClass> carClasses = carClassService.getAllCarClasses();
        List<CarClassDTO> carClassDTOS = new ArrayList<>();

        for (CarClass c : carClasses) {
            if (!c.isRemoved()){
                carClassDTOS.add(new CarClassDTO(c));
            }
        }

        logger.info("CarC-read"); //CarC-car class
        return new ResponseEntity<>(carClassDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('car_class_create')")
    public ResponseEntity<CarClassDTO> addCarClass(@RequestBody CarClassDTO carClassDTO) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        CarClass carClass = carClassService.addCarClass(carClassDTO);

        if (carClass != null) {
            logger.info(MessageFormat.format("CarC-ID:{0}-created;UserID:{1}", carClass.getId(), cp.getUserID()));
            return new ResponseEntity<>(new CarClassDTO(carClass), HttpStatus.CREATED);
        }
        logger.warn(MessageFormat.format("CarC-not created;UserID:{0}", cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('car_class_delete')")
    public ResponseEntity<?> removeCarClass(@PathVariable Long id) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        if (carClassService.removeCarClass(id)) {
            logger.info(MessageFormat.format("CarC-ID:{0}-deleted;UserID:{1}", id, cp.getUserID()));
            return new ResponseEntity<>(HttpStatus.OK);
        }
        logger.info(MessageFormat.format("CarC-ID:{0}-not deleted;UserID:{1}", id, cp.getUserID()));
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
