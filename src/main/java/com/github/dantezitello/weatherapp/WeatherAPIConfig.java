package com.github.dantezitello.weatherapp;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WeatherAPIConfig {


    @Value("${meteo-api.history-url}")
    String weatherHistoryUrl;

    public WeatherAPIConfig() {
    }

    public String getWeatherHistoryUrl() {
        return weatherHistoryUrl;
    }

    public void setWeatherHistoryUrl(String weatherHistoryUrl) {
        this.weatherHistoryUrl = weatherHistoryUrl;
    }
}
