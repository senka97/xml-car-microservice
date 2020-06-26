package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.CarClass;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CarClassDTO {

    private Long id;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9_ ]*$", message="Name must contain only letters and numbers.")
    private String name;

    public CarClassDTO() {
    }

    public CarClassDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public CarClassDTO(CarClass carClass) {
        this.id = carClass.getId();
        this.name = carClass.getName();
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
