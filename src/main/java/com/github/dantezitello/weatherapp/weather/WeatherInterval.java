package com.github.dantezitello.weatherapp.weather;

import java.time.LocalDate;

public class WeatherInterval {

    private LocalDate start;
    private LocalDate end;

    private WeatherInterval(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }


    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }


    public boolean isSingle() {
        return start == end;
    }

    public static WeatherInterval of(LocalDate date) {
        return new WeatherInterval(date, date);
    }

    public static WeatherInterval from(LocalDate start, LocalDate end) {
        return new WeatherInterval(start, end);
    }

}


