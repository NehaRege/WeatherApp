package com.test.myapplication.data.model.Location;

public class Location {
    public double latitude;
    public double longitude;
    public String name;

    public Location(double latitude, double longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public Location(double latitude, double longitude, String name) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.name = name;
    }
}
