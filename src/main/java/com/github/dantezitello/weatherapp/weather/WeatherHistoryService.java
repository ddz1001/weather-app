package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.WeatherAppConfig;
import com.github.dantezitello.weatherapp.common.*;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.scheduler.Schedulers;

import java.math.BigDecimal;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

@Service
public class WeatherHistoryService {

    WebClient webClient;
    WeatherAppConfig config;


    @Autowired
    public WeatherHistoryService(WeatherAppConfig config) {
        this.config = config;

        webClient = WebClient.create(config.getWeatherHistoryUrl());
    }

    public WeatherHistoryResult fetchHistoryForLocation(GeographicCoordinates coordinates, WeatherHistoryOptions option, LocalDate startRange, LocalDate endRange) throws WeatherAPIException {


        WeatherHistoryResult result = null;
        WeatherHistoryModel model;

        UnitType type;
        boolean convertToKelvin = false;
        if(option.getType() == UnitType.KELVIN) {
            type = UnitType.CELSIUS;
            convertToKelvin = true;
        }
        else {
            type = option.getType();
        }

        switch(option.getInterval()) {

            case DAILY -> {
                model = fetch(coordinates, type, startRange, endRange);
                result = WeatherResultAggregation.aggregateDaily(model);
            }
            case WEEKLY -> {
                startRange = LocalDateAdjustments.adjustToWeekStart(startRange);
                endRange = LocalDateAdjustments.adjustToWeekEnd(endRange);

                model = fetch(coordinates, type, startRange, endRange);
                result = WeatherResultAggregation.aggregateWeekly(model);
            }
            case MONTHLY -> {
                startRange = LocalDateAdjustments.adjustToMonthStart(startRange);
                endRange = LocalDateAdjustments.adjustToMonthEnd(endRange);

                model = fetch(coordinates, type, startRange, endRange);
                result = WeatherResultAggregation.aggregateMonthly(model);
            }
            case YEARLY -> {
                List<LocalDateRange> years = LocalDateAdjustments.splitYearlyIntoRanges(startRange.getYear(), endRange.getYear());
                result = new WeatherHistoryResult();

                WeatherHistoryModel curModel;
                for(LocalDateRange year : years) {
                    curModel = fetch(coordinates, type, year.getStart(), year.getEnd());
                    result.getWeatherEntries().addAll( WeatherResultAggregation.aggregateYearly(curModel).getWeatherEntries()  );
                }
            }
        }

        if(convertToKelvin) {
            BigDecimal kelvin = new BigDecimal("273.15");
            result.getWeatherEntries().replaceAll(
                    (average) -> {
                        return new RecordedAverage(average.getInterval(), average.getTemperature().add(kelvin), UnitType.KELVIN );
                    }
            );
        }

        return result;
    }


    private WeatherHistoryModel fetch(GeographicCoordinates coordinates, UnitType type, LocalDate startRange, LocalDate endRange) {


        URI uri = UriComponentsBuilder.fromUriString(config.getWeatherHistoryUrl())
                .queryParam("latitude", coordinates.getLatitude())
                .queryParam("longitude", coordinates.getLongitude())
                .queryParam("temperature_unit", type.getFullName())
                .queryParam("timezone", "GMT")
                .queryParam("daily", "temperature_2m_max")
                .queryParam("start_date", startRange)
                .queryParam("end_date", endRange).build().toUri();

        try {
            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono( WeatherHistoryModel.class )
                    .subscribeOn(Schedulers.boundedElastic())
                    .toFuture()
                    .get(10_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new WeatherAPIException(e);
        }
    }


}
