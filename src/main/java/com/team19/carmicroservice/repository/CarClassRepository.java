package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.CarClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarClassRepository extends JpaRepository<CarClass, Long> {

    CarClass findByName(String name);
}
