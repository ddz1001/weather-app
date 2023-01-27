package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.WeatherAPIConfig;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import com.github.dantezitello.weatherapp.geolocation.model.GeolocationInfo;
import com.github.dantezitello.weatherapp.geolocation.model.GeolocationModel;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.function.Predicate;

@Service
public class GeolocationService {


    WeatherAPIConfig config;
    WebClient webClient;

    @Autowired
    public GeolocationService(WeatherAPIConfig config) {
        this.config = config;
        webClient = WebClient.create(config.getGeolocationUrl());
    }

    public GeolocationResult findByCityName(String cityName) throws WeatherAPIException {
        return null;
    }

    public GeolocationResult findByCityCountry(String cityName, String countryCode) throws WeatherAPIException {
        return null;
    }

    public GeolocationResult findByCityCountryAdminRegion(String cityName, String countryCode, String administrativeRegion) throws WeatherAPIException {
        return null;
    }

    private GeolocationInfo query(GeolocationModel model, Predicate<GeolocationInfo> predicate) {
        return model.getCities().stream().filter( predicate ).findFirst().orElse(null);
    }

    private GeolocationModel fetch(String cityName) {
        URI uri = UriComponentsBuilder.fromUriString(config.getGeolocationUrl())
                .queryParam("name", cityName)
                .queryParam("count", config.getCityQueryMax())
                .queryParam("format", "json")
                .queryParam("language", "en").build().toUri();


        return webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono( GeolocationModel.class )
                .block();
    }


}
