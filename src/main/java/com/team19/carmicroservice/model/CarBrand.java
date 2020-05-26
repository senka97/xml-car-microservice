package com.team19.carmicroservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "car_brand", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<CarModel> carModels;

    public CarBrand() {
    }

    public CarBrand(Long id, String name) {
        this.id = id;
        this.name = name;
        this.carModels = new HashSet<CarModel>();
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

    public Set<CarModel> getCarModels() {
        return carModels;
    }

    public void setCarModels(Set<CarModel> carModels) {
        this.carModels = carModels;
    }
}
