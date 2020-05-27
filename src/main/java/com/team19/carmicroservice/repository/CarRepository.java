package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {

}
