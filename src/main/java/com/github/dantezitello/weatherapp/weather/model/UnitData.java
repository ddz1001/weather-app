package com.github.dantezitello.weatherapp.weather.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.UnitType;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UnitData {



    @JsonProperty("temperature_2m_max")
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
