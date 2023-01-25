package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UnitData {



    @JsonProperty("temperature_2m")
    UnitType unitType;

    public UnitData() {
    }

    public UnitType getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitType unitType) {
        this.unitType = unitType;
    }
}
