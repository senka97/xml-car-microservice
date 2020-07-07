package com.team19.carmicroservice.dto;

public class MapData {

    private double lat;
    private double lng;
    private String token;

    public MapData(){

    }

    public MapData(double lat, double lng, String token){
        this.lat = lat;
        this.lng = lng;
        this.token = token;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
