package com.team19.carmicroservice.dto;

public class MapData {

    private Position position;
    private String androidToken;

    public MapData(){

    }

    public MapData(Position position, String androidToken){
        this.position = position;
        this.androidToken = androidToken;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public String getAndroidToken() {
        return androidToken;
    }

    public void setAndroidToken(String androidToken) {
        this.androidToken = androidToken;
    }
}
