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
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private static final Logger logger = LoggerFactory.getLogger(GeolocationService.class);

    @Autowired
    public GeolocationService(WeatherAppConfig config) {
        this.config = config;
        webClient = WebClient.create(config.getGeolocationUrl());
    }

    public GeolocationResult find(String cityName) throws WeatherAPIException {
        logger.info("Querying for city with name: {}", cityName);

        //Default query
        Predicate<GeolocationInfo> firstPass = (geo) -> {
            return geo.getName().equalsIgnoreCase(cityName);
        };

        //If not found, we try again with an ascii normalized city name
        Predicate<GeolocationInfo> secondPass = (geo) -> {
            return StringUtils.stripAccents(geo.getName()).equalsIgnoreCase(cityName);
        };

        GeolocationModel model = fetch(cityName);

        GeolocationInfo info = query( model, firstPass);
        if(info == null) {
            info = query(model, secondPass);
        }

        if(info == null) {
            throw new WeatherAPIException(cityName + " was not found.");
        }


        return convert(info);
    }

    public GeolocationResult find(String cityName, Country country) throws WeatherAPIException {
        logger.info("Querying for city with name: {} in country: {}", cityName, country);


        //Default query
        Predicate<GeolocationInfo> firstPass = (geo) -> {
            return geo.getName().equalsIgnoreCase(cityName)
                    && geo.getCountryCode() == country;
        };

        //If not found, we try again with an ascii normalized city name
        Predicate<GeolocationInfo> secondPass = (geo) -> {
            return StringUtils.stripAccents(geo.getName()).equalsIgnoreCase(cityName)
                    && geo.getCountryCode() == country;
        };

        GeolocationModel model = fetch(cityName);

        GeolocationInfo info = query( model, firstPass);
        if(info == null) {
            info = query(model, secondPass);
        }

        if(info == null) {
            throw new WeatherAPIException(cityName + " [country-code]:" + country.getTwoLetterCode() + " was not found.");
        }

        return convert(info);
    }

    public GeolocationResult find(String cityName, Country country, String administrativeRegion) throws WeatherAPIException {
        logger.info("Querying for city with name: {} in country: {} in admin-region: {} ", cityName, country, administrativeRegion);

        //Default query
        Predicate<GeolocationInfo> firstPass = (geo) -> {
            return geo.getName().equalsIgnoreCase(cityName)
                    && geo.getCountryCode() == country
                    && geo.getAdministrativeRegion().equalsIgnoreCase(administrativeRegion);
        };

        //If not found, we try again with an ascii normalized city name
        Predicate<GeolocationInfo> secondPass = (geo) -> {
            return StringUtils.stripAccents(geo.getName()).equalsIgnoreCase(cityName)
                    && geo.getCountryCode() == country
                    && geo.getAdministrativeRegion().equalsIgnoreCase(administrativeRegion);
        };

        //If still not found, we try one final time with city and admin region being normalized
        Predicate<GeolocationInfo> thirdPass = (geo) -> {
            return  StringUtils.stripAccents(geo.getName()).equalsIgnoreCase(cityName)
                    && geo.getCountryCode() == country
                    && StringUtils.stripAccents(geo.getAdministrativeRegion()).equalsIgnoreCase(administrativeRegion);
        };

        /*
            Querying here is done in 3 passes. This is done as some admin regions and city names contain accents that
            may not be present on a standard keyboard. This is not done by default as stripping the accents
            immediately can cause inaccuracy, and so it is done as a last resort if nothing is found.
         */
        GeolocationModel model = fetch(cityName);

        GeolocationInfo info = query( model, firstPass);
        if(info == null) {
            info = query(model, secondPass);
        }

        if(info == null) {
            info = query(model, thirdPass);
        }


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
                    .log()
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
