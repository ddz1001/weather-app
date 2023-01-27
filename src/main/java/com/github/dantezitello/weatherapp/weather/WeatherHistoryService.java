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
import java.util.List;

@Service
public class WeatherHistoryService {

    WebClient webClient;
    WeatherAPIConfig config;


    public static enum AggregationOption {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    @Autowired
    public WeatherHistoryService(WeatherAPIConfig config) {
        this.config = config;

        webClient = WebClient.create(config.getWeatherHistoryUrl());
    }

    public WeatherHistoryResult fetchHistoryForLocation(GeographicCoordinates coordinates, AggregationOption option, LocalDate startRange, LocalDate endRange) throws WeatherAPIException {


        WeatherHistoryResult result = null;
        WeatherHistoryModel model;

        switch(option) {

            case DAILY -> {
                model = fetch(coordinates, startRange, endRange);
                result = WeatherResultAggregation.aggregateDaily(model);
            }
            case WEEKLY -> {
                startRange = LocalDateAdjustments.adjustToWeekStart(startRange);
                endRange = LocalDateAdjustments.adjustToWeekEnd(endRange);

                model = fetch(coordinates, startRange, endRange);
                result = WeatherResultAggregation.aggregateWeekly(model);
            }
            case MONTHLY -> {
                startRange = LocalDateAdjustments.adjustToMonthStart(startRange);
                endRange = LocalDateAdjustments.adjustToMonthEnd(endRange);

                model = fetch(coordinates, startRange, endRange);
                result = WeatherResultAggregation.aggregateMonthly(model);
            }
            case YEARLY -> {
                List<LocalDateRange> years = LocalDateAdjustments.splitYearlyIntoRanges(startRange.getYear(), endRange.getYear());
                result = new WeatherHistoryResult();

                WeatherHistoryModel curModel;
                for(LocalDateRange year : years) {
                    curModel = fetch(coordinates, year.getStart(), year.getEnd());
                    result.getWeatherEntries().addAll( WeatherResultAggregation.aggregateYearly(curModel).getWeatherEntries()  );
                }
            }
        }

        fetch(coordinates, startRange, endRange);

        return result;
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
                .bodyToMono( WeatherHistoryModel.class )
                .cache();


        return historyModelMono.block();
    }


}
