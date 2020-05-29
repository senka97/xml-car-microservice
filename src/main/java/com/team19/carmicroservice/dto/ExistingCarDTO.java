package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.model.FuelType;

import java.util.ArrayList;

public class ExistingCarDTO {

    private Long id;

    private int childrenSeats;

    private double rate;

    private double mileage;

    private boolean hasAndroidApp;

    private CarBrandDTO carBrand;

    private CarModelDTO carModel;

    private CarClassDTO carClass;

    private TransmissionTypeDTO transType;

    private FuelTypeDTO fuelType;

    private ArrayList<String> photos64 = new ArrayList<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public boolean isHasAndroidApp() {
        return hasAndroidApp;
    }

    public void setHasAndroidApp(boolean hasAndroidApp) {
        this.hasAndroidApp = hasAndroidApp;
    }

    public CarBrandDTO getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(CarBrandDTO carBrand) {
        this.carBrand = carBrand;
    }

    public CarModelDTO getCarModel() {
        return carModel;
    }

    public void setCarModel(CarModelDTO carModel) {
        this.carModel = carModel;
    }

    public CarClassDTO getCarClass() {
        return carClass;
    }

    public void setCarClass(CarClassDTO carClass) {
        this.carClass = carClass;
    }

    public TransmissionTypeDTO getTransType() {
        return transType;
    }

    public void setTransType(TransmissionTypeDTO transType) {
        this.transType = transType;
    }

    public FuelTypeDTO getFuelType() {
        return fuelType;
    }

    public void setFuelType(FuelTypeDTO fuelType) {
        this.fuelType = fuelType;
    }

    public ArrayList<String> getPhotos64() {
        return photos64;
    }

    public void setPhotos64(ArrayList<String> photos64) {
        this.photos64 = photos64;
    }
}
