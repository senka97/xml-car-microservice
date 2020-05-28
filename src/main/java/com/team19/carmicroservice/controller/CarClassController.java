package com.team19.carmicroservice.controller;

import com.team19.carmicroservice.dto.CarClassDTO;
import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.service.impl.CarClassServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/class", produces = MediaType.APPLICATION_JSON_VALUE)
public class CarClassController {

    @Autowired
    private CarClassServiceImpl carClassService;

    @GetMapping
    public ResponseEntity<List<CarClassDTO>> getAllCarClasses() {

        List<CarClass> carClasses = carClassService.getAllCarClasses();
        List<CarClassDTO> carClassDTOS = new ArrayList<>();

        for (CarClass c : carClasses) {
            if (!c.isRemoved()){
                carClassDTOS.add(new CarClassDTO(c));
            }
        }

        return new ResponseEntity<>(carClassDTOS, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    @PreAuthorize("hasAuthority('addCarClass')")
    public ResponseEntity<CarClassDTO> addCarClass(@RequestBody CarClassDTO carClassDTO) {
        CarClass carClass = carClassService.addCarClass(carClassDTO);

        if (carClass != null) {
            return new ResponseEntity<>(new CarClassDTO(carClass), HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('removeCarClass')")
    public ResponseEntity<?> removeCarClass(@PathVariable Long id) {

        if (carClassService.removeCarClass(id)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
