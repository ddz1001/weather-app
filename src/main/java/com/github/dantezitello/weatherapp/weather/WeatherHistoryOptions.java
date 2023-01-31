package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;

public class WeatherHistoryOptions {

    UnitType type;
    Interval interval;

    public WeatherHistoryOptions(UnitType type, Interval interval) {
        this.type = type;
        this.interval = interval;
    }

    public UnitType getType() {
        return type;
    }

    public Interval getInterval() {
        return interval;
    }

    public static WeatherHistoryOptions getDefault() {
        return new WeatherHistoryOptions(UnitType.CELSIUS, Interval.DAILY);
    }


}
