package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.FuelType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelTypeRepository extends JpaRepository<FuelType, Long> {

    FuelType findByName(String name);
}
