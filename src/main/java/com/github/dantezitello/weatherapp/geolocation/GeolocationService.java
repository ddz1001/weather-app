package com.github.dantezitello.weatherapp.geolocation;

import com.github.dantezitello.weatherapp.WeatherAppConfig;
import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.GeographicCoordinates;
import com.github.dantezitello.weatherapp.common.WeatherAPIException;
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

    public GeolocationResult find(String cityName, String countryCode) throws WeatherAPIException {
        GeolocationInfo info = query( fetch(cityName),
                geo -> geo.getName().equalsIgnoreCase(cityName) && geo.getCountryCode().equalsIgnoreCase(countryCode)
        );
        if(info == null) {
            throw new WeatherAPIException(cityName + " [country-code]:" + countryCode + " was not found.");
        }

        return convert(info);
    }

    public GeolocationResult find(String cityName, String countryCode, String administrativeRegion) throws WeatherAPIException {
        GeolocationInfo info = query( fetch(cityName),
                geo -> geo.getName().equalsIgnoreCase(cityName) && geo.getCountryCode().equalsIgnoreCase(countryCode) && geo.getAdministrativeRegion().equalsIgnoreCase(administrativeRegion)
        );
        if(info == null) {
            throw new WeatherAPIException(cityName + " [country-code]:" + countryCode
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
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private GeolocationResult convert(GeolocationInfo info) {
        return new GeolocationResult(
                new CityInfo(
                        info.getName(),
                        info.getCountryName(),
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
