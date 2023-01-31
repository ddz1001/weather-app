package com.github.dantezitello.weatherapp.common;

import com.github.dantezitello.weatherapp.common.administration.Country;

public class CityInfo {

    String cityName;
    Country country;
    String administrativeRegion;

    public CityInfo(String cityName, Country country, String administrativeRegion) {
        this.cityName = cityName;
        this.country = country;
        this.administrativeRegion = administrativeRegion;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public String getAdministrativeRegion() {
        return administrativeRegion;
    }

    public void setAdministrativeRegion(String administrativeRegion) {
        this.administrativeRegion = administrativeRegion;
    }
}
