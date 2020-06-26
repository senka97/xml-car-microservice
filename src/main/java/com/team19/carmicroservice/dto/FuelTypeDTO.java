package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.FuelType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class FuelTypeDTO {

    private Long id;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9_ ]*$", message="Name must contain only letters and numbers.")
    private String name;

    public FuelTypeDTO() {
    }

    public FuelTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public FuelTypeDTO(FuelType fuelType) {
        this.id = fuelType.getId();
        this.name = fuelType.getName();
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
