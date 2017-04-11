package com.mohamed.rami.firebase;

/**
 * Created by Rami on 20/03/2017.
 */

public class NICUPosition {
    private String longitude;
    private String latitude;
    private String phone;
    private String Address;
    private String price;
    private String nicuCount;
    private String nicuName;
    private String description;
    private String date;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNicuCount() {
        return nicuCount;
    }

    public void setNicuCount(String nicuCount) {
        this.nicuCount = nicuCount;
    }

    public String getNicuName() {
        return nicuName;
    }

    public void setNicuName(String nicuName) {
        this.nicuName = nicuName;
    }

    public String getSituations() {
        return Situations;
    }

    public void setSituations(String situations) {
        Situations = situations;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    private String Situations;

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
