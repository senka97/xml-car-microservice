package com.team19.carmicroservice.rabbitmq;

import com.google.gson.Gson;
import com.team19.carmicroservice.dto.MapData;
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
        MapData mapData = gson.fromJson(message,MapData.class);
        Position position = new Position(mapData.getLat(),mapData.getLng());
        System.out.println("Pozicija:" + position.getLat() + "," + position.getLng());
        this.simpMessagingTemplate.convertAndSend("/socket-publisher/" + mapData.getToken(), message);

    }
}
