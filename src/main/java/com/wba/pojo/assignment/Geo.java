package com.wba.pojo.assignment;

public class Geo {
    private String lat;

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    private String lng;

    public Geo(){}

    public Geo(String lat,String lng){
        this.lat=lat;
        this.lng=lng;
    }


}
