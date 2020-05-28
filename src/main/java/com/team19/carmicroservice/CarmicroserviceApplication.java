package com.team19.carmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
public class CarmicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarmicroserviceApplication.class, args);
    }

}
