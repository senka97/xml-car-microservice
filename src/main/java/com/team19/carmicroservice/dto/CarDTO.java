package com.team19.carmicroservice.dto;

import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;

public class CarDTO {

    private Long id;

    private int childrenSeats;

    private double rate;

    private double mileage;

    private boolean hasAndroidApp;

    private String androidToken;

    private String carBrand;

    private String carModel;

    private String carClass;

    private String transType;

    private String fuelType;

    private ArrayList<String> photos64 = new ArrayList<>();


    public CarDTO(){

    }

    public CarDTO(Car car){
        {
            this.setId(car.getId());
            this.setChildrenSeats(car.getChildrenSeats());
            this.setRate(car.getRate());
            this.setMileage(car.getMileage());
            this.setHasAndroidApp(car.getHasAndroidApp());
            this.setAndroidToken(car.getAndroidToken());
            this.setCarBrand(car.getCarModel().getCarBrand().getName());
            this.setCarModel(car.getCarModel().getName());
            this.setCarClass(car.getCarClass().getName());
            this.setTransType(car.getTransmissionType().getName());
            this.setFuelType(car.getFuelType().getName());
            //photos

            if(car.getImages() != null) {
                for (Image p : car.getImages()) {
                    ByteArrayOutputStream bos = new ByteArrayOutputStream();
                    Path path = Paths.get(p.getPath());
                    // write the image to a file
                    System.out.println(p.getPath());
                    File input = path.toFile();
                    BufferedImage img = null;
                    try {
                        img = ImageIO.read(input);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if(img != null) {
                        try {
                            ImageIO.write(img, "png", bos);
                            byte[] imageBytes = bos.toByteArray();


                            String imageString = Base64.getEncoder().encodeToString(imageBytes);
                            String retStr = "data:image/png;base64," + imageString;
                            this.getPhotos64().add(retStr);
                            bos.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }

        }
    }

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

    public String getCarBrand() {
        return carBrand;
    }

    public void setCarBrand(String carBrand) {
        this.carBrand = carBrand;
    }

    public String getCarModel() {
        return carModel;
    }

    public void setCarModel(String carModel) {
        this.carModel = carModel;
    }

    public String getCarClass() {
        return carClass;
    }

    public void setCarClass(String carClass) {
        this.carClass = carClass;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public boolean getHasAndroidApp() {
        return hasAndroidApp;
    }

    public void setHasAndroidApp(boolean hasAndroidApp) {
        this.hasAndroidApp = hasAndroidApp;
    }

    public ArrayList<String> getPhotos64() {
        return photos64;
    }

    public void setPhotos64(ArrayList<String> photos64) {
        this.photos64 = photos64;
    }


    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }
}
