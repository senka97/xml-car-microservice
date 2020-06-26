package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.CarModel;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CarModelDTO {

    private Long id;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9_ ]*$", message="Name must contain only letters and numbers.")
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
