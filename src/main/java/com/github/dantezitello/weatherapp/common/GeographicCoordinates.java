package com.github.dantezitello.weatherapp.common;

import java.math.BigDecimal;

public class GeographicCoordinates {

    public final static GeographicCoordinates EMPTY = new GeographicCoordinates(null, null);

    BigDecimal latitude;
    BigDecimal longitude;

    public GeographicCoordinates(double latitude, double longitude) {
        this.latitude = new BigDecimal(latitude);
        this.longitude = new BigDecimal(longitude);
    }
    public GeographicCoordinates(BigDecimal latitude, BigDecimal longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }
}
