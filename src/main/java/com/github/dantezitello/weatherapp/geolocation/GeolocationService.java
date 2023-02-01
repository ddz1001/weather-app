/*
 * Weather Charting Project
 * Copyright (C) 2023 Dante Zitello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.WeatherAppConfig;
import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.geolocation.model.GeolocationInfo;
import com.github.dantezitello.weatherapp.geolocation.model.GeolocationModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.scheduler.Schedulers;

import java.net.URI;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.function.Predicate;

@Service
public class GeolocationService {


    WeatherAppConfig config;
    WebClient webClient;

    @Autowired
    public GeolocationService(WeatherAppConfig config) {
        this.config = config;
        webClient = WebClient.create(config.getGeolocationUrl());
    }

    public GeolocationResult find(String cityName) throws WeatherAPIException {
        GeolocationInfo info = query( fetch(cityName), geo -> geo.getName().equalsIgnoreCase(cityName) ); //select first result
        if(info == null) {
            throw new WeatherAPIException(cityName + " was not found.");
        }


        return convert(info);
    }

    public GeolocationResult find(String cityName, Country country) throws WeatherAPIException {
        GeolocationInfo info = query( fetch(cityName),
                geo -> geo.getName().equalsIgnoreCase(cityName) && geo.getCountryCode() == country
        );
        if(info == null) {
            throw new WeatherAPIException(cityName + " [country-code]:" + country.getTwoLetterCode() + " was not found.");
        }

        return convert(info);
    }

    public GeolocationResult find(String cityName, Country country, String administrativeRegion) throws WeatherAPIException {
        GeolocationInfo info = query( fetch(cityName),
                geo -> geo.getName().equalsIgnoreCase(cityName) && geo.getCountryCode() == country && geo.getAdministrativeRegion().equalsIgnoreCase(administrativeRegion)
        );
        if(info == null) {
            throw new WeatherAPIException(cityName + " [country-code]:" + country.getTwoLetterCode()
                    + " [admin-region]:" + administrativeRegion + " was not found.");
        }



        return convert(info);
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


        try {
            return webClient.get()
                    .uri(uri)
                    .retrieve()
                    .bodyToMono( GeolocationModel.class )
                    .subscribeOn(Schedulers.boundedElastic())
                    .toFuture()
                    .get(10_000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private GeolocationResult convert(GeolocationInfo info) {
        return new GeolocationResult(
                new CityInfo(
                        info.getName(),
                        info.getCountryCode(),
                        info.getAdministrativeRegion()
                ),
                new GeographicCoordinates(
                        info.getLatitude(),
                        info.getLongitude()
                )
        );
    }


}
