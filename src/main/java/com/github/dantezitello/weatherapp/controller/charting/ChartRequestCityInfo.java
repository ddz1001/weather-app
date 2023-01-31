package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.administration.CanadianProvince;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.common.administration.MexicanState;
import com.github.dantezitello.weatherapp.common.administration.UnitedStatesState;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class ChartRequestCityInfo {

    @JsonProperty(value = "city-name", required = true)
    String cityName;
    @JsonProperty("country")
    Country country;
    @JsonProperty("usa-state")
    UnitedStatesState usaState;
    @JsonProperty("can-province")
    CanadianProvince canProvince;
    @JsonProperty("mex-state")
    MexicanState mexState;
    @JsonProperty("admin-region")
    String adminRegion;

    public ChartRequestCityInfo() {
    }

    public boolean isUSA() {
        return Objects.nonNull(usaState);
    }

    public boolean isCAN() {
        return Objects.nonNull( canProvince );
    }

    public boolean isMEX() {
        return Objects.nonNull( mexState );
    }

    public boolean hasAdmin() {
        return Objects.nonNull(adminRegion);
    }

    public boolean hasCountry() {
        return Objects.nonNull(country);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public UnitedStatesState getUsaState() {
        return usaState;
    }

    public void setUsaState(UnitedStatesState usaState) {
        this.usaState = usaState;
    }

    public CanadianProvince getCanProvince() {
        return canProvince;
    }

    public void setCanProvince(CanadianProvince canProvince) {
        this.canProvince = canProvince;
    }

    public MexicanState getMexState() {
        return mexState;
    }

    public void setMexState(MexicanState mexState) {
        this.mexState = mexState;
    }

    public String getAdminRegion() {
        return adminRegion;
    }

    public void setAdminRegion(String adminRegion) {
        this.adminRegion = adminRegion;
    }
}
