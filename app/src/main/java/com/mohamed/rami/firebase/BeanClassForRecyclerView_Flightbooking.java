package com.mohamed.rami.firebase;

/**
 * Created by admin on 3/22/2016.
 */
public class BeanClassForRecyclerView_Flightbooking {


    Integer image;
    String departure;
    String city;
    String airport;
    String departure1;
    String city1;
    String airport1;
    String time;
    String price;

    public BeanClassForRecyclerView_Flightbooking(Integer image, String departure, String city, String airport, String departure1, String city1, String airport1, String time, String price) {
        this.image = image;
        this.departure = departure;
        this.city = city;
        this.airport = airport;
        this.departure1 = departure1;
        this.city1 = city1;
        this.airport1 = airport1;
        this.time = time;
        this.price = price;
    }

    public Integer getImage() {
        return image;
    }

    public void setImage(Integer image) {
        this.image = image;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAirport() {
        return airport;
    }

    public void setAirport(String airport) {
        this.airport = airport;
    }

    public String getDeparture1() {
        return departure1;
    }

    public void setDeparture1(String departure1) {
        this.departure1 = departure1;
    }

    public String getCity1() {
        return city1;
    }

    public void setCity1(String city1) {
        this.city1 = city1;
    }

    public String getAirport1() {
        return airport1;
    }

    public void setAirport1(String airport1) {
        this.airport1 = airport1;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}



