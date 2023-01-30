package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.UnitType;

public class ChartOptions {

    public static enum ChartType {
        LINECHART,
        BARCHART
    }

    public static enum IntervalType {
        DAILY,
        WEEKLY,
        MONTHLY,
        YEARLY
    }

    public static enum ContentType {
        SVG,
        PNG
    }



    ChartType displayType;
    IntervalType intervalType;
    ContentType contentType;
    UnitType unitType;


    public ChartOptions(ChartType displayType, IntervalType intervalType, ContentType contentType, UnitType unitType) {
        this.displayType = displayType;
        this.intervalType = intervalType;
        this.contentType = contentType;
        this.unitType = unitType;
    }

    public ChartType getDisplayType() {
        return displayType;
    }

    public IntervalType getIntervalType() {
        return intervalType;
    }

    public ContentType getContentType() {
        return contentType;
    }

    public UnitType getUnitType() {
        return unitType;
    }
}