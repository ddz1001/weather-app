package com.github.dantezitello.weatherapp.common;

public class CityInfo {

    String cityName;
    String countryName;
    String countryCode;
    String administrativeRegion;
    String administrativeCode;

    public CityInfo(String cityName, String countryName, String countryCode, String administrativeRegion, String administrativeCode) {
        this.cityName = cityName;
        this.countryName = countryName;
        this.countryCode = countryCode;
        this.administrativeRegion = administrativeRegion;
        this.administrativeCode = administrativeCode;
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

    public String getAdministrativeCode() {
        return administrativeCode;
    }
}
