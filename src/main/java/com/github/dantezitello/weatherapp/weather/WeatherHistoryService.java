package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.WeatherAPIConfig;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDate;

@Service
public class WeatherHistoryService {

    WebClient webClient;
    WeatherAPIConfig config;

    @Autowired
    public WeatherHistoryService(WeatherAPIConfig config) {
        this.config = config;

        webClient = WebClient.create(config.getWeatherHistoryUrl());
    }

    public WeatherHistoryResult fetchHistoryForLocation(GeographicCoordinates coordinates, LocalDate startRange, LocalDate endRange) throws WeatherAPIException {
        fetch(coordinates, startRange, endRange);
        return null;
    }


    private WeatherHistoryModel fetch(GeographicCoordinates coordinates, LocalDate startRange, LocalDate endRange) {

        Mono<WeatherHistoryModel> historyModelMono;

        URI uri = UriComponentsBuilder.fromUriString(config.getWeatherHistoryUrl())
                .queryParam("latitude", coordinates.getLatitude())
                .queryParam("longitude", coordinates.getLongitude())
                .queryParam("temperature_unit", "celsius")
                .queryParam("timezone", "GMT")
                .queryParam("daily", "temperature_2m_max")
                .queryParam("start_date", startRange)
                .queryParam("end_date", endRange).build().toUri();

        historyModelMono = webClient.get()
                .uri(uri)
                .retrieve()
                .bodyToMono( WeatherHistoryModel.class );


        return historyModelMono.block();
    }


}
