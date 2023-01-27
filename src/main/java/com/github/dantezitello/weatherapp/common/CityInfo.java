package com.github.dantezitello.weatherapp.common;

public class CityInfo {

    String cityName;
    String countryName;
    String countryCode;
    String administrativeRegion;

    public CityInfo(String cityName, String countryName, String countryCode, String administrativeRegion) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.administrativeRegion = administrativeRegion;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public String getAdministrativeRegion() {
        return administrativeRegion;
    }
}
