package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.CarModel;

public class CarModelDTO {

    private Long id;
    private String name;

    public CarModelDTO() {
    }

    public CarModelDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarModelDTO(CarModel carModel) {
        this.id = carModel.getId();
        this.name = carModel.getName();
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
