package com.github.dantezitello.weatherapp.controller;


import com.github.dantezitello.weatherapp.charting.ChartingService;
import com.github.dantezitello.weatherapp.controller.charting.*;
import com.github.dantezitello.weatherapp.geolocation.GeolocationService;
import com.github.dantezitello.weatherapp.weather.WeatherHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        return null;
    }





}
