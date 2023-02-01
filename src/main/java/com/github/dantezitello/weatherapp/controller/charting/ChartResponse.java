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

package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.LinkedHashMap;
import java.util.Map;

public class ChartResponse {

    @JsonProperty("response-data")
    Object responseData;
    @JsonProperty("error-data")
    Object errorData;
    public static ChartResponse createResponse(String key) {

        Map<String, Object> entries = new LinkedHashMap<>();
        entries.put("resource-key", key);

        ChartResponse response = new ChartResponse();
        response.responseData = entries;
        response.errorData = null;
        return response;
    }

    public static ChartResponse createErrorResponse(Throwable error) {
        ChartResponse response = new ChartResponse();
        response.responseData = null;
        response.errorData = Map.of( "error", error.getMessage() );

        return response;
    }
}
