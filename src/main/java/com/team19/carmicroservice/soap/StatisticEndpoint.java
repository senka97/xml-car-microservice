package com.team19.carmicroservice.soap;

import com.rent_a_car.car_service.soap.*;
import com.team19.carmicroservice.dto.CarStatisticDTO;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.impl.CarServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.transaction.Transactional;
import java.awt.*;
import java.text.MessageFormat;
import java.util.ArrayList;

@Endpoint
public class StatisticEndpoint {

    @Autowired
    private CarServiceImpl carService;

    private static final String NAMESPACE_URI = "http://www.rent-a-car.com/car-service/soap";

    Logger logger = LoggerFactory.getLogger(StatisticEndpoint.class);

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCarWithMostCommentsRequest")
    @ResponsePayload
    @Transactional
    @PreAuthorize("hasAuthority('car_read')")
    public GetCarWithMostCommentsResponse getCarWithMostComments(@RequestPayload GetCarWithMostCommentsRequest request) {
        System.out.println("Most Comments");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        GetCarWithMostCommentsResponse response = new GetCarWithMostCommentsResponse();
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

        logger.info(MessageFormat.format("SRCar-ID:{0}-most comments read;UserID:{1}", carStatisticDTO.getId(), cp.getUserID()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCarWithMostKilometersRequest")
    @ResponsePayload
    @Transactional
    @PreAuthorize("hasAuthority('car_read')")
    public GetCarWithMostKilometersResponse getCarWithMostComments(@RequestPayload GetCarWithMostKilometersRequest request) {
        System.out.println("Most kilometers");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        GetCarWithMostKilometersResponse response = new GetCarWithMostKilometersResponse();
        CarStatisticDTO carStatisticDTO = carService.getCarWithMostKilometers();

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
        logger.info(MessageFormat.format("SRCar-ID:{0}-most kilometers read;UserID:{1}", carStatisticDTO.getId(), cp.getUserID()));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "GetCarWithBestRateRequest")
    @ResponsePayload
    @Transactional
    @PreAuthorize("hasAuthority('car_read')")
    public GetCarWithBestRateResponse getCarWithMostComments(@RequestPayload GetCarWithBestRateRequest request) {
        System.out.println("Best Rate");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        GetCarWithBestRateResponse response = new GetCarWithBestRateResponse();
        CarStatisticDTO carStatisticDTO = carService.getCarWithBestScore();

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
        logger.info(MessageFormat.format("SRCar-ID:{0}-best score read;UserID:{1}", carStatisticDTO.getId(), cp.getUserID()));
        return response;
    }
}
