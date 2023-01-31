package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Interval {
    @JsonProperty("daily") DAILY,
    @JsonProperty("weekly") WEEKLY,
    @JsonProperty("monthly") MONTHLY,
    @JsonProperty("yearly") YEARLY
    ;


}
