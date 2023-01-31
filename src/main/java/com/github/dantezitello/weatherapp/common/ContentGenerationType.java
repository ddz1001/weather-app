package com.github.dantezitello.weatherapp.common;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;

public enum ContentGenerationType {

    @JsonProperty("png") @JsonAlias("image/png") PNG("png", "image/png"),
    @JsonProperty("svg") @JsonAlias("image/svg+xml") SVG("svg", "image/svg+xml")
    ;
    private final String suffix;
    private final String mimeTypeString;

    ContentGenerationType(String suffix, String mimeTypeString) {
        this.suffix = suffix;
        this.mimeTypeString = mimeTypeString;
    }

    public String getSuffix() {
        return suffix;
    }

    public String getMimeTypeString() {
        return mimeTypeString;
    }
}
