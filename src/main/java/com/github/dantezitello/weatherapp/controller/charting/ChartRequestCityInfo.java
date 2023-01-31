package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

public class ChartRequestCityInfo {

    @JsonProperty(value = "city-name", required = true)
    String cityName;
    @JsonProperty("country-name")
    String countryName;
    @JsonProperty("country-code")
    String countryCode;
    @JsonProperty("usa-state")
    String usaState;
    @JsonProperty("can-province")
    String canProvince;
    @JsonProperty("mex-state")
    String mexState;
    @JsonProperty("admin-region")
    String adminRegion;

    public ChartRequestCityInfo() {
    }

    public boolean isUSA() {
        return StringUtils.isNotBlank( usaState );
    }

    public boolean isCAN() {
        return StringUtils.isNotBlank( canProvince );
    }

    public boolean isMEX() {
        return StringUtils.isNotBlank( mexState );
    }

    public boolean hasAdmin() {
        return StringUtils.isNotBlank(adminRegion);
    }

    public boolean hasCountryCode() {
        return StringUtils.isNotBlank(countryCode);
    }

    public boolean hasCountryFull() {
        return StringUtils.isNotBlank(countryName);
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getUsaState() {
        return usaState;
    }

    public void setUsaState(String usaState) {
        this.usaState = usaState;
    }

    public String getCanProvince() {
        return canProvince;
    }

    public void setCanProvince(String canProvince) {
        this.canProvince = canProvince;
    }

    public String getMexState() {
        return mexState;
    }

    public void setMexState(String mexState) {
        this.mexState = mexState;
    }

    public String getAdminRegion() {
        return adminRegion;
    }

    public void setAdminRegion(String adminRegion) {
        this.adminRegion = adminRegion;
    }
}
