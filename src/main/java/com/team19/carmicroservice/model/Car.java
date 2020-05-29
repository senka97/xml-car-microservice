package com.team19.carmicroservice.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarClass carClass;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private CarModel carModel;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private TransmissionType transmissionType;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private FuelType fuelType;

    @Column(name="children_seats")
    private int childrenSeats;

    @Column(name="rate")
    private double rate;

    @Column(name="mileage")
    private double mileage;

    @Column(name="has_android_app")
    private boolean hasAndroidApp;

    @Column(name="owner_id")
    private Long ownerId;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Image> images;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "car", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Report> reports;

    public Car() {
    }

    public Car(Long id, CarClass carClass, CarModel carModel, TransmissionType transmissionType, FuelType fuelType,
               int childrenSeats, double rate, double mileage, boolean hasAndroidApp) {
        this.id = id;
        this.carClass = carClass;
        this.carModel = carModel;
        this.transmissionType = transmissionType;
        this.fuelType = fuelType;
        this.childrenSeats = childrenSeats;
        this.rate = rate;
        this.mileage = mileage;
        this.hasAndroidApp = hasAndroidApp;
        this.images = new HashSet<>();
        this.comments = new HashSet<>();
        this.reports = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CarClass getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClass carClass) {
        this.carClass = carClass;
    }

    public CarModel getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModel carModel) {
        this.carModel = carModel;
    }

    public TransmissionType getTransmissionType() {
        return transmissionType;
    }

    public void setTransmissionType(TransmissionType transmissionType) {
        this.transmissionType = transmissionType;
    }

    public FuelType getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelType fuelType) {
        this.fuelType = fuelType;
    }

    public int getChildrenSeats() {
        return childrenSeats;
    }

    public void setChildrenSeats(int childrenSeats) {
        this.childrenSeats = childrenSeats;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getMileage() {
        return mileage;
    }

    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public boolean getHasAndroidApp() {
        return hasAndroidApp;
    }

    public void setHasAndroidApp(boolean hasAndroidApp) {
        this.hasAndroidApp = hasAndroidApp;
    }

    public Set<Image> getImages() {
        return images;
    }

    public void setImages(Set<Image> images) {
        this.images = images;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

    public Set<Report> getReports() {
        return reports;
    }

    public void setReports(Set<Report> reports) {
        this.reports = reports;
    }

    public boolean isHasAndroidApp() {
        return hasAndroidApp;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
}
