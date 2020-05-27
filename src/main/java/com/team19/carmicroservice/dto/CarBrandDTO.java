package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.CarBrand;

public class CarBrandDTO {

    private Long id;
    private String name;

    public CarBrandDTO() {
    }

    public CarBrandDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarBrandDTO(CarBrand carBrand) {
        this.id = carBrand.getId();
        this.name = carBrand.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
