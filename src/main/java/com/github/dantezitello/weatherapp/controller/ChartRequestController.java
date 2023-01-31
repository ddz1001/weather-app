package com.github.dantezitello.weatherapp.controller;


import com.github.dantezitello.weatherapp.charting.ChartingService;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.LocalDateRange;
import com.github.dantezitello.weatherapp.common.UnitType;
import com.github.dantezitello.weatherapp.controller.charting.*;
import com.github.dantezitello.weatherapp.geolocation.GeolocationResult;
import com.github.dantezitello.weatherapp.geolocation.GeolocationService;
import com.github.dantezitello.weatherapp.geolocation.administration.CanadaAdminRegion;
import com.github.dantezitello.weatherapp.geolocation.administration.Country;
import com.github.dantezitello.weatherapp.geolocation.administration.MexicoAdminRegion;
import com.github.dantezitello.weatherapp.geolocation.administration.UnitedStatesAdminRegion;
import com.github.dantezitello.weatherapp.graphics.ChartData;
import com.github.dantezitello.weatherapp.graphics.ChartDataBuilder;
import com.github.dantezitello.weatherapp.graphics.ChartOptions;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryResult;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<ChartResponse> generateChart(@RequestBody ChartRequest request, @RequestParam boolean print) {
        List<ChartRequestCityInfo> infoList = request.getCities();
        List<GeolocationResult> coordinates = findAllForInfo(infoList);

        LocalDateRange range = convert(request.getDateRange());


        ChartData data = compileDataForLocations(coordinates,range,request.getDetails().getGroupingOption() );
        ChartOptions options = new ChartOptions(ChartOptions.ChartType.BARCHART, convertToInterval(request.getDetails().getGroupingOption()), ChartOptions.ContentType.SVG, UnitType.CELSIUS );

        String key = chartingService.createChart(data, options);

        return ResponseEntity.ok( ChartResponse.createResponseKeyOnly(key) );
    }


    private ChartData compileDataForLocations(List<GeolocationResult> results, LocalDateRange range, ChartGroupingOption option) {
        ChartDataBuilder builder = new ChartDataBuilder();
        results.forEach( result -> {
            WeatherHistoryResult historyResult = findHistoryForLocation(result.getCoordinates(), range, convertToOption(option));
            builder.addInfo(result.getCityInfo(), historyResult.getWeatherEntries());
        } );

        return builder.build();
    }

    private WeatherHistoryResult findHistoryForLocation(GeographicCoordinates geo, LocalDateRange range, WeatherHistoryService.AggregationOption option) {
        return historyService.fetchHistoryForLocation(geo, option, range.getStart(), range.getEnd());
    }

    private List<GeolocationResult> findAllForInfo(List<ChartRequestCityInfo> infoList) {
        List<GeolocationResult> result = new ArrayList<>();
        infoList.forEach( info -> result.add(findForInfo(info)) );
        return result;
    }

    private GeolocationResult findForInfo(ChartRequestCityInfo info) {

        String cityName = null;
        String countryCode = null;
        String admin = null;


        //Always required
        cityName = info.getCityName();

        //Determine the country, we can check for the special fields first
        if(info.isUSA()) {
            countryCode = Country.UNITED_STATES.getTwoLetterCode();
        }
        else if(info.isCAN()) {
            countryCode = Country.CANADA.getTwoLetterCode();
        }
        else if(info.isMEX()) {
            countryCode = Country.MEXICO.getTwoLetterCode();
        }
        else if(!info.hasCountryCode() && info.hasCountryFull()) {
            countryCode = Optional.ofNullable(Country.findByName(info.getCountryName()))
                    .orElseThrow(() -> new RuntimeException("Country name not valid"))
                    .getTwoLetterCode();
        }
        else if(info.hasCountryCode()) {
            if(info.getCountryCode().length() == 3) {
                countryCode = Optional.ofNullable(Country.findByThreeLetterCode(info.getCountryCode()))
                        .orElseThrow(() -> new RuntimeException("Country code not valid"))
                        .getTwoLetterCode();
            }
            else if(info.getCountryCode().length() == 2) {
                countryCode = Optional.ofNullable(Country.findByTwoLetterCode(info.getCountryCode()))
                        .orElseThrow(() -> new RuntimeException("Country code not valid"))
                        .getTwoLetterCode();
            }
            else {
                throw new RuntimeException("Country code not valid");
            }
        }
        else {
            countryCode = null;
        }

        //determine the admin region, there may not be one
        if(info.isUSA()) {
            if(info.getUsaState().length() == 2) {
                admin = Optional.ofNullable( UnitedStatesAdminRegion.findByAbbreviation(info.getUsaState()) )
                        .orElseThrow(() -> new RuntimeException("US state code not valid"))
                        .getFullName();
            }
            else {
                admin = info.getUsaState();
            }
        }
        else if(info.isCAN()) {
            if(info.getCanProvince().length() == 2) {
                admin = Optional.ofNullable( CanadaAdminRegion.findByAbbreviation(info.getCanProvince()) )
                        .orElseThrow(() -> new RuntimeException("Country code not valid"))
                        .getFullName();
            }
            else {
                admin = info.getCanProvince();
            }
        }
        else if(info.isMEX()) {
            if(info.getMexState().length() == 2) {
                admin = Optional.ofNullable( MexicoAdminRegion.findByAbbreviation(info.getMexState()) )
                        .orElseThrow(() -> new RuntimeException("Country code not valid"))
                        .getFullName();
            }
            else {
                admin = info.getMexState();
            }
        }
        else if(info.hasAdmin()) {
            admin = info.getAdminRegion();
        }
        else {
            admin = null;
        }

        //fetch the geographic coordinates
        GeolocationResult result = null;

        if(cityName != null && countryCode != null && admin != null) {
            result = geolocationService.find(cityName, countryCode, admin);
        }
        else if(cityName != null && countryCode != null) {
            result = geolocationService.find(cityName, countryCode);
        }
        else if(cityName != null) {
            result = geolocationService.find(cityName);
        }
        else {
            throw new RuntimeException("No city name was provided");
        }

        return result;
    }

    private WeatherHistoryService.AggregationOption convertToOption(ChartGroupingOption option) {
        switch(option) {

            case DAILY -> {
                return WeatherHistoryService.AggregationOption.DAILY;
            }
            case WEEKLY -> {
                return WeatherHistoryService.AggregationOption.WEEKLY;
            }
            case MONTHLY -> {
                return WeatherHistoryService.AggregationOption.MONTHLY;
            }
            case YEARLY -> {
                return WeatherHistoryService.AggregationOption.YEARLY;
            }
        }
        return null;
    }

    private ChartOptions.IntervalType convertToInterval(ChartGroupingOption option) {
        switch (option) {

            case DAILY -> {
                return ChartOptions.IntervalType.DAILY;
            }
            case WEEKLY -> {
                return ChartOptions.IntervalType.WEEKLY;
            }
            case MONTHLY -> {
                return ChartOptions.IntervalType.MONTHLY;
            }
            case YEARLY -> {
                return ChartOptions.IntervalType.YEARLY;
            }
        }
        return null;
    }

    private LocalDateRange convert(ChartRequestDateRange range) {
        return LocalDateRange.from(range.getStartDate(), range.getEndDate());
    }


}
