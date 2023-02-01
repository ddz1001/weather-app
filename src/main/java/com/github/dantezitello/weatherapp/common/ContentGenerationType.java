/*
 * Weather Charting Project
 * Copyright (C) 2023 Dante Zitello
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

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
