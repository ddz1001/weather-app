package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import org.springframework.stereotype.Service;

@Service
public class GeolocationService {


    public GeolocationResult findByCityName(String cityName) throws WeatherAPIException {
        return null;
    }

    public GeolocationResult findByCityCountry(String cityName, String countryCode) throws WeatherAPIException {
        return null;
    }

    public GeolocationResult findByCityCountryAdminRegion(String cityName, String countryCode, String administrativeRegion) throws WeatherAPIException {
        return null;
    }

}
