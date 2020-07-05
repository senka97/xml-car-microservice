package com.team19.carmicroservice;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableFeignClients
public class CarmicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CarmicroserviceApplication.class, args);
    }

    @Value("${host}")
    String host;

    // Registrujemo bean koji ce sluziti za konekciju na RabbitMQ na localhostu
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host);
        return connectionFactory;
    }


}
