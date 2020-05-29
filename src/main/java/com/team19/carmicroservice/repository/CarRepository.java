package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;

public interface CarRepository extends JpaRepository<Car, Long> {
    ArrayList<Car> findAllByOwnerId(Long id);
}
