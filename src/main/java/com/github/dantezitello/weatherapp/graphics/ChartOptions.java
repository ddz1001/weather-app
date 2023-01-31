package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.ChartStyling;
import com.github.dantezitello.weatherapp.common.ContentGenerationType;
import com.github.dantezitello.weatherapp.common.Interval;
import com.github.dantezitello.weatherapp.common.UnitType;

public class ChartOptions {

    ChartStyling displayType;
    Interval intervalType;
    ContentGenerationType contentType;
    UnitType unitType;


    public ChartOptions(ChartStyling displayType, Interval intervalType, ContentGenerationType contentType, UnitType unitType) {
        this.displayType = displayType;
        this.intervalType = intervalType;
        this.contentType = contentType;
        this.unitType = unitType;
    }

    public ChartStyling getDisplayType() {
        return displayType;
    }

    public Interval getIntervalType() {
        return intervalType;
    }

    public ContentGenerationType getContentType() {
        return contentType;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}
