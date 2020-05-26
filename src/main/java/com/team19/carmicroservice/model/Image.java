package com.team19.carmicroservice.model;

import javax.persistence.*;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="path")
    private String path;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Car car;

    public Image() {
    }

    public Image(Long id, String path, Car car) {
        this.id = id;
        this.path = path;
        this.car = car;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }
}
