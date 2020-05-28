package com.team19.carmicroservice.service;

import com.team19.carmicroservice.dto.AdDTO;
import com.team19.carmicroservice.dto.CarDTO;

import java.util.ArrayList;
import java.util.List;

public interface CarService {

    CarDTO getCar(Long carId);
    ArrayList<AdDTO> findCars(ArrayList<AdDTO> ads);
}
