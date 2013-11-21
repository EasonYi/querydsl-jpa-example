package com.querydsl.jpa.example.data;

public class LocationData {

    private final Double longitude;

    private final Double latitude;

    public LocationData(Double longitude, Double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

}
