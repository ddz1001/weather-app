package com.github.dantezitello.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherAPIConfig {


    @Value("${meteo-api.history-url}")
    String weatherHistoryUrl;

    @Value("${meteo-api.geolocator-url}")
    String geolocationUrl;

    @Value("100")
    int cityQueryMax;

    public WeatherAPIConfig() {
    }

    public String getWeatherHistoryUrl() {
        return weatherHistoryUrl;
    }

    public void setWeatherHistoryUrl(String weatherHistoryUrl) {
        this.weatherHistoryUrl = weatherHistoryUrl;
    }

    public String getGeolocationUrl() {
        return geolocationUrl;
    }

    public void setGeolocationUrl(String geolocationUrl) {
        this.geolocationUrl = geolocationUrl;
    }

    public int getCityQueryMax() {
        return cityQueryMax;
    }

    public void setCityQueryMax(int cityQueryMax) {
        this.cityQueryMax = cityQueryMax;
    }
}
