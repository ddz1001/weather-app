package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum ChartStyling {

    @JsonProperty("line") LINE("Line"),
    @JsonProperty("bar")  BAR("Bar");

    private final String styleName;

    ChartStyling(String styleName) {
        this.styleName = styleName;
    }

    public String getStyleName() {
        return styleName;
    }
}
