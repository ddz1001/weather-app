package com.github.dantezitello.weatherapp.geolocation.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GeolocationModel {

    @JsonProperty("results")
    List<GeolocationInfo> cities;



    public GeolocationModel() {
        cities = new ArrayList<>();
    }

    public List<GeolocationInfo> getCities() {
        return cities;
    }

    public void setCities(List<GeolocationInfo> cities) {
        this.cities = cities;
    }
}
