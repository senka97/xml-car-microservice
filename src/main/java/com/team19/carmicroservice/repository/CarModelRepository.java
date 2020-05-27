package com.team19.carmicroservice.repository;

import com.team19.carmicroservice.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    List<CarModel> findByCarBrand_Id(Long id);
}
