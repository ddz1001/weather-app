package com.github.dantezitello.weatherapp.controller;


import com.github.dantezitello.weatherapp.charting.ChartingService;
import com.github.dantezitello.weatherapp.common.*;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.controller.charting.*;
import com.github.dantezitello.weatherapp.geolocation.GeolocationResult;
import com.github.dantezitello.weatherapp.geolocation.GeolocationService;
import com.github.dantezitello.weatherapp.graphics.ChartData;
import com.github.dantezitello.weatherapp.graphics.ChartDataBuilder;
import com.github.dantezitello.weatherapp.graphics.ChartOptions;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryOptions;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryResult;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryService;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import org.apache.commons.lang3.tuple.Triple;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("charting")
public class ChartRequestController {

    @Autowired
    GeolocationService geolocationService;

    @Autowired
    ChartingService chartingService;

    @Autowired
    WeatherHistoryService historyService;



    @PostMapping("/create")
    public ResponseEntity<ChartResponse> generateChart(@RequestBody ChartRequest request) {


        Triple<UnitType, ChartStyling, Interval> tuple = extractOptions(request);
        UnitType unitType = tuple.getLeft();
        ChartStyling styling = tuple.getMiddle();
        Interval intervalType = tuple.getRight();
        LocalDateRange range = LocalDateRange.from(request.getDateRange().getStartDate(), request.getDateRange().getEndDate());

        List<ChartRequestCityInfo> cityRequest = request.getCities();
        List<GeolocationResult> locations = locateAll(cityRequest);

        ChartData data = recordAll(locations, unitType, intervalType, range);
        String resourceKey = chartingService.createChart(data, new ChartOptions(styling, intervalType, ContentGenerationType.SVG, unitType));

        return ResponseEntity.ok( ChartResponse.createResponse(resourceKey) );
    }

    private ChartData recordAll(List<GeolocationResult> results, UnitType units, Interval interval, LocalDateRange range) {
        ChartDataBuilder builder = new ChartDataBuilder();
        WeatherHistoryOptions options = new WeatherHistoryOptions(units, interval);


        for(GeolocationResult result : results) {
            WeatherHistoryResult resultForCity = recordFor(result, options, range);
            builder.addInfo(result.getCityInfo(), resultForCity.getWeatherEntries());
        }

        return builder.build();
    }

    private WeatherHistoryResult recordFor(GeolocationResult geolocationResult, WeatherHistoryOptions options, LocalDateRange range) {
        return historyService.fetchHistoryForLocation( geolocationResult.getCoordinates(), options, range.getStart(), range.getEnd());
    }

    private List<GeolocationResult> locateAll(List<ChartRequestCityInfo> requestInfo) {
        List<GeolocationResult> locations = new ArrayList<>();
        requestInfo.forEach( info -> locations.add( locate(info) ) );
        return locations;
    }

    private GeolocationResult locate(ChartRequestCityInfo cityInfo) {
        String cityName = cityInfo.getCityName();
        Country country = null;
        String admin = null;


        if (cityInfo.isUSA()) {
            country = Country.UNITED_STATES;
            admin = cityInfo.getUsaState().englishName();
        } else if (cityInfo.isCAN()) {
            country = Country.CANADA;
            admin = cityInfo.getCanProvince().englishName();
        } else if (cityInfo.isMEX()) {
            country = Country.MEXICO;
            admin = cityInfo.getMexState().englishName();
        } else if (cityInfo.hasCountry()) {
            country = cityInfo.getCountry();
            if (cityInfo.hasAdmin()) {
                admin = cityInfo.getAdminRegion();
            }
        }

        if (country != null && admin != null) {
            return geolocationService.find(cityName, country, admin);
        } else if (country != null) {
            return geolocationService.find(cityName, country);
        } else {
            return geolocationService.find(cityName);
        }
    }

    private Triple<UnitType, ChartStyling, Interval> extractOptions(ChartRequest request) {

        if(Objects.isNull(request.getDetails())) {
            return new ImmutableTriple<>(UnitType.CELSIUS, ChartStyling.BAR, Interval.DAILY);
        }

        ChartRequestChartDetails details = request.getDetails();

        return new ImmutableTriple<>(
            Objects.requireNonNullElse(details.getUnitOption(), UnitType.CELSIUS),
            Objects.requireNonNullElse(details.getStyleOption(), ChartStyling.BAR),
            Objects.requireNonNullElse(details.getGroupingOption(), Interval.DAILY)
        );
    }




}
