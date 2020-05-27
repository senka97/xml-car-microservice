package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.CarBrand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarBrandRepository extends JpaRepository<CarBrand, Long> {
}
