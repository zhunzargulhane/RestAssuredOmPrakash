package com.wba.pojo.assignment;

public class Address {
    private Geo geo;
    private String street;
    private String suite;
    private String city;
    private String zipcode;


    public Address(){}

    public Address(String street,String suite,String city,String zipcode,Geo geo){
        this.geo=geo;
        this.street=street;
        this.suite=suite;
        this.city=city;
        this.zipcode=zipcode;
    }

    public Geo getGeo() {
        return geo;
    }

    public void setGeo(Geo geo) {
        this.geo = geo;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }



}
