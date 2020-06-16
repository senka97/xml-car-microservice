package com.team19.carmicroservice.service.impl;

import com.mysql.cj.util.Base64Decoder;
import com.team19.carmicroservice.client.AdClient;
import com.team19.carmicroservice.dto.*;
import com.team19.carmicroservice.model.Car;
import com.team19.carmicroservice.model.CarClass;
import com.team19.carmicroservice.model.CarModel;
import com.team19.carmicroservice.model.Image;
import com.team19.carmicroservice.repository.CarRepository;
import com.team19.carmicroservice.security.CustomPrincipal;
import com.team19.carmicroservice.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;


@Service
public class CarServiceImpl implements CarService {

    @Autowired
    private CarRepository carRepository;

    @Override
    public Car getCarById(Long id) {
        return carRepository.findById(id).orElse(null);
    }
    @Autowired
    private CarModelServiceImpl carModelService;

    @Autowired
    private CarClassServiceImpl carClassService;

    @Autowired
    private FuelTypeServiceImpl fuelTypeService;

    @Autowired
    private TransmissionTypeServiceImpl transmissionTypeService;

    @Autowired
    private ImageServiceImpl imageService;

    @Autowired
    private AdClient adClient;

    @Override
    public CarDTO getCar(Long id) {

        CarDTO carDTO = new CarDTO();

        Car car = carRepository.findById(id).orElse(null);

            if(car != null)
            {
                carDTO.setId(car.getId());
                carDTO.setChildrenSeats(car.getChildrenSeats());
                carDTO.setRate(car.getRate());
                carDTO.setMileage(car.getMileage());
                carDTO.setHasAndroidApp(car.getHasAndroidApp());
                carDTO.setCarBrand(car.getCarModel().getCarBrand().getName());
                carDTO.setCarModel(car.getCarModel().getName());
                carDTO.setCarClass(car.getCarClass().getName());
                carDTO.setTransType(car.getTransmissionType().getName());
                carDTO.setFuelType(car.getFuelType().getName());

                if(car.getImages() != null) {
                    for (Image p : car.getImages()) {
                        System.out.println("**********FOTOGRAFIJA********");
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
                                carDTO.getPhotos64().add(retStr);
                                bos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


            }
            else
            {
                return null;
            }

        return carDTO;
    }

    @Override
    public ArrayList<AdDTO> findCars(ArrayList<AdDTO> ads) {

        for( AdDTO ad : ads)
        {
            CarDTO carDTO = ad.getCar();

            Car car = carRepository.findById(ad.getCar().getId()).orElse(null);

            if(car != null)
            {
                carDTO.setId(car.getId());
                carDTO.setChildrenSeats(car.getChildrenSeats());
                carDTO.setRate(car.getRate());
                carDTO.setMileage(car.getMileage());
                carDTO.setHasAndroidApp(car.getHasAndroidApp());
                carDTO.setCarBrand(car.getCarModel().getCarBrand().getName());
                carDTO.setCarModel(car.getCarModel().getName());
                carDTO.setCarClass(car.getCarClass().getName());
                carDTO.setTransType(car.getTransmissionType().getName());
                carDTO.setFuelType(car.getFuelType().getName());
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
                                carDTO.getPhotos64().add(retStr);
                                bos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

            }
        }
        return ads;
    }

    @Override
    public CarDTO addNewCar(CarDTO carDTO) {
        System.out.println("****************NOVI AUTO***************");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        Car car = new Car();
        car.setCarModel(carModelService.findByBrandAndModelName(carDTO.getCarBrand(),carDTO.getCarModel()));
        System.out.println(car.getCarModel().getName());
        car.setCarClass(carClassService.findByName(carDTO.getCarClass()));
        car.setChildrenSeats(carDTO.getChildrenSeats());
        car.setFuelType(fuelTypeService.findByName(carDTO.getFuelType()));
        car.setTransmissionType(transmissionTypeService.findByName(carDTO.getTransType()));
        car.setHasAndroidApp(carDTO.getHasAndroidApp());
        car.setMileage(carDTO.getMileage());
        car.setOwnerId(Long.parseLong(cp.getUserID()));

        //Dodavanje Slika
        //Prima slike kao base64 string prebacuje ih u slike i snima ih u folder carPictures
        //taj folder morate napraviti kod sebe na C da bi radilo
        //slika se snima po templejtu /carPictures/idCar_brSlike.png
        Car newCar = carRepository.save(car);

        int i= 0;
        if(carDTO.getPhotos64() !=null) {
            for (String photo : carDTO.getPhotos64()) {


                // create a buffered image
                String[] parts = photo.split(",");
                String imageString = parts[1];


                BufferedImage image = null;
                byte[] imageByte;


                Base64Decoder decoder = new Base64Decoder();
                imageByte = Base64.getDecoder().decode(imageString);
                ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
                try {
                    image = ImageIO.read(bis);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String folder = "./carPicturesMain/";

                Path directoryPath = Paths.get(folder);
                File directory = directoryPath.toFile();
                if (!directory.exists()) {
                    directory.mkdir();
                }

                Path path = Paths.get(folder + car.getId() + "_" + i + ".png");
                // write the image to a file
                File outputfile = path.toFile();
                try {
                    image = resize(image,800,550);
                    ImageIO.write(image, "png", outputfile);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Image p = new Image();
                p.setCar(car);
                p.setPath(folder + car.getId() + "_" + i + ".png");


                imageService.save(p);

                i++;
            }
        }
        carDTO.setId(newCar.getId());
        System.out.println("****************DODAT***************");
        return carDTO;
    }

    @Override
    public ArrayList<ExistingCarDTO> getCarsWithNoActiveAds() {
        System.out.println("GETTUJEM");
        ArrayList<ExistingCarDTO> carDTOS = new ArrayList<>();

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        ArrayList<Car> cars = carRepository.findAllByOwnerId(Long.parseLong(cp.getUserID()));

        if(cars == null) {
            System.out.println("NULL");
            return carDTOS;
        }
        for(Car car: cars){
            AdDTO adDTO= adClient.checkIfCarHasActiveAds(car.getId(),cp.getPermissions(),cp.getUserID(),cp.getToken());
            if(adDTO == null){
                ExistingCarDTO carDTO = new ExistingCarDTO();
                carDTO.setId(car.getId());
                carDTO.setCarBrand(new CarBrandDTO(car.getCarModel().getCarBrand()));
                carDTO.setCarModel(new CarModelDTO(car.getCarModel()));
                carDTO.setCarClass(new CarClassDTO(car.getCarClass()));
                carDTO.setChildrenSeats(car.getChildrenSeats());
                carDTO.setFuelType(new FuelTypeDTO(car.getFuelType()));
                carDTO.setHasAndroidApp(car.getHasAndroidApp());
                carDTO.setMileage(car.getMileage());
                carDTO.setRate(car.getRate());
                carDTO.setTransType(new TransmissionTypeDTO(car.getTransmissionType()));

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
                                carDTO.getPhotos64().add(retStr);
                                bos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }


                System.out.println("Car slobodan "+car.getId());
                carDTOS.add(carDTO);
            }

        }
        return carDTOS;
    }

    @Override
    public ArrayList<CarDTO> searchCars(String brand, String model, String feulType, String classType, String transType, int mileage, int childrenSeats) {
        List<Car> cars = carRepository.findAll();
        ArrayList<CarDTO> carDTOS = new ArrayList<>();
        for(Car car:cars){

            if(!brand.equals("all")){
                if(!car.getCarModel().getCarBrand().getName().equals(brand)){
                    continue;
                }
            }

            if(!model.equals("all")){
                if(!car.getCarModel().getName().equals(model)){
                    continue;
                }
            }

            if(!feulType.equals("all")){
                if(!car.getFuelType().getName().equals(feulType)){
                    continue;
                }
            }

            if(!classType.equals("all")){
                if(!car.getCarClass().getName().equals(classType)){
                    continue;
                }
            }

            if(mileage != 0){
                if(car.getMileage() > mileage){
                    continue;
                }
            }

            if(childrenSeats != 0){
                if(car.getChildrenSeats() < childrenSeats){
                    continue;
                }
            }
            CarDTO carDTO = new CarDTO(car);
            carDTOS.add(carDTO);
        }
        return carDTOS;
    }

    @Override
    public boolean rating(Long userId, Long adId, double rate) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();

        AdDTOSimple ad = adClient.getAdSimple(adId);
        if (ad != null) {
            Car car = carRepository.getOne(ad.getCarId());

            if (car != null) {
                double newRate = (car.getRate() + rate) / 2;
                car.setRate(newRate);

                //promeniti da je korisnik ocenio auto
                if (adClient.changeUserCanRate(userId, car.getId(), cp.getPermissions(), cp.getUserID(), cp.getToken())) {
                    carRepository.save(car);
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    @Override
    public CarStatisticDTO getCarWithMostComments() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        List<Car> cars = carRepository.findAllByOwnerId(Long.parseLong(cp.getUserID()));
        Car mostComments = cars.get(0);
        System.out.println("Pocetak");
        for(Car car: cars){
            if(car.getComments() != null) {

                if (mostComments == null)
                    mostComments = car;

                if (mostComments.getComments().size() < car.getComments().size())
                    mostComments = car;
            }
        }
        System.out.println("Kraj");
        CarStatisticDTO carStatisticDTO = new CarStatisticDTO(mostComments);
        carStatisticDTO.setNumberOfComments(mostComments.getComments().size());
        return carStatisticDTO;
    }


    @Override
    public CarStatisticDTO getCarWithMostKilometers() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        List<Car> cars = carRepository.findAllByOwnerId(Long.parseLong(cp.getUserID()));
        Car mostKilometers = cars.get(0);
        for(Car car: cars){
            if(mostKilometers.getMileage() < car.getMileage())
                mostKilometers=car;
        }
        CarStatisticDTO carStatisticDTO = new CarStatisticDTO(mostKilometers);
        carStatisticDTO.setNumberOfComments(mostKilometers.getComments().size());
        return carStatisticDTO;
    }

    @Override
    public CarStatisticDTO getCarWithBestScore() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        CustomPrincipal cp = (CustomPrincipal) auth.getPrincipal();
        List<Car> cars = carRepository.findAllByOwnerId(Long.parseLong(cp.getUserID()));
        Car bestScore = cars.get(0);
        for(Car car: cars){
            if(bestScore.getRate() < car.getRate())
                bestScore=car;
        }
        CarStatisticDTO carStatisticDTO = new CarStatisticDTO(bestScore);
        carStatisticDTO.setNumberOfComments(bestScore.getComments().size());
        return carStatisticDTO;
    }
    /*

      TODO Pogledati

      Neka funkcija da pomogne oko encodovanja slike
      Ako je ne resizujem npr treba mu 80 milisekundi da je encoduje
      A recimo ako je resizujem na 500*500 treba mu 12 milisekundi
      Druga stvar za koju mu treba puno je citanje slike treba mu oko 80 milisekunidi (snimljena je u punoj velicini)
      ali mislim da kad je snimimo resizovanu trebace mu manje

      Predlog je naravno da resizujemo na neku velicinu pri snimanju i recimo da ogranicimo broj slika koje oglas moze da ima na tipa 3
    */
    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        java.awt.Image tmp = img.getScaledInstance(newW, newH, java.awt.Image.SCALE_SMOOTH);
        BufferedImage dimg = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dimg.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();

        return dimg;
    }

}
