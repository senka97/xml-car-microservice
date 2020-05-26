package com.team19.carmicroservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class CarClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name", nullable=false)
    private String name;

    @OneToMany(mappedBy = "car_class", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Car> cars;

    public CarClass() {
    }

    public CarClass(Long id, String name) {
        this.id = id;
        this.name = name;
        this.cars = new HashSet<>();
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

    public Set<Car> getCars() {
        return cars;
    }

    public void setCars(Set<Car> cars) {
        this.cars = cars;
    }
}
