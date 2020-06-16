package com.team19.carmicroservice.soap;

import com.rent_a_car.car_service.soap.*;
import com.team19.carmicroservice.dto.CarStatisticDTO;
import com.team19.carmicroservice.service.impl.CarServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import java.awt.*;
import java.util.ArrayList;

@Endpoint
public class StatisticEndpoint {

    @Autowired
    private CarServiceImpl carService;

    private static final String NAMESPACE_URI = "http://www.rent-a-car.com/car-service/soap";


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "MostCommentsRequest")
    @ResponsePayload
    @Transactional
    public MostCommentsResponse getCarWithMostComments(@RequestPayload MostCommentsRequest request) {
        System.out.println("Most Comments");
        MostCommentsResponse response = new MostCommentsResponse();
        CarStatisticDTO carStatisticDTO = carService.getCarWithMostComments();

        CarSOAP carSOAP = new CarSOAP();

        carSOAP.setCarBrand(carStatisticDTO.getCarBrand());
        carSOAP.setCarClass(carStatisticDTO.getCarClass());
        carSOAP.setCarModel(carStatisticDTO.getCarModel());
        carSOAP.setChildrenSeats(carStatisticDTO.getChildrenSeats());
        carSOAP.setFeulType(carStatisticDTO.getFuelType());
        carSOAP.setHasAndroidApp(carStatisticDTO.isHasAndroidApp());
        carSOAP.setMileage(carStatisticDTO.getMileage());
        carSOAP.setId(carStatisticDTO.getId());
        carSOAP.setNumberOfComments(carStatisticDTO.getNumberOfComments());
        carSOAP.setRate(carStatisticDTO.getRate());
        carSOAP.setTransType(carStatisticDTO.getTransType());


        for(String img: carStatisticDTO.getPhotos64()){
            carSOAP.getPhotos64().add(img);
        }

        response.setCarSOAP(carSOAP);

        System.out.println(carStatisticDTO.getCarModel());
        return response;
    }
}