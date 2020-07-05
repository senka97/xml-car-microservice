package com.team19.carmicroservice.rabbitmq;

import com.google.gson.Gson;
import com.team19.carmicroservice.dto.Position;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RabbitListener(queues="${locationqueue}")
    public void handlerForLocation(String message) {
        System.out.println(message);
        Gson gson = new Gson();
        Position position = gson.fromJson(message, Position.class);
        System.out.println("Pozicija:" + position.getLat() + "," + position.getLng());
        this.simpMessagingTemplate.convertAndSend("/socket-publisher", message);

    }
}
