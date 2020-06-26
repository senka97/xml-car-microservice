package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.TransmissionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class TransmissionTypeDTO {

    private Long id;

    @NotBlank(message="Name must not be empty.")
    @Pattern(regexp="^[a-zA-Z0-9_ ]*$", message="Name must contain only letters and numbers.")
    private String name;

    public TransmissionTypeDTO() {
    }

    public TransmissionTypeDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public TransmissionTypeDTO(TransmissionType transmissionType) {
        this.id = transmissionType.getId();
        this.name = transmissionType.getName();
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
