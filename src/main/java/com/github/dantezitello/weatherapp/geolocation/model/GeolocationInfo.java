package com.github.dantezitello.weatherapp.geolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.administration.Country;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationInfo {

    String name;
    BigDecimal latitude;
    BigDecimal longitude;

    @JsonProperty("country_code")
    Country countryCode;

    @JsonProperty("admin1")
    String administrativeRegion;

    public GeolocationInfo() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLatitude() {
        return latitude;
    }

    public void setLatitude(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public BigDecimal getLongitude() {
        return longitude;
    }

    public void setLongitude(BigDecimal longitude) {
        this.longitude = longitude;
    }

    public Country getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(Country countryCode) {
        this.countryCode = countryCode;
    }

    public String getAdministrativeRegion() {
        return administrativeRegion;
    }

    public void setAdministrativeRegion(String administrativeRegion) {
        this.administrativeRegion = administrativeRegion;
    }
}
