package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;

public class GeolocationResult {
    CityInfo cityInfo;
    GeographicCoordinates coordinates;

    public GeolocationResult(CityInfo cityInfo, GeographicCoordinates coordinates) {
        this.cityInfo = cityInfo;
        this.coordinates = coordinates;
    }

    public CityInfo getCityInfo() {
        return cityInfo;
    }

    public GeographicCoordinates getCoordinates() {
        return coordinates;
    }
}
