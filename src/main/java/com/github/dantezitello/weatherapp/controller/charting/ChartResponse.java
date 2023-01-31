package com.github.dantezitello.weatherapp.controller.charting;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.RecordedAverage;
import com.github.dantezitello.weatherapp.graphics.ChartData;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ChartResponse {

    @JsonProperty("response-data")
    Object responseData;

    @JsonProperty("error-data")
    Object errorData;



    public static ChartResponse createResponseKeyOnly(String key) {

        Map<String, Object> entries = new LinkedHashMap<>();
        entries.put("resource-key", key);

        ChartResponse response = new ChartResponse();
        response.responseData = entries;
        response.errorData = null;
        return response;
    }

    public static ChartResponse createResponseWithKeyAndData(String key, ChartData data) {
        ChartResponse response = new ChartResponse();

        Map<String, Object> entries = new LinkedHashMap<>();
        entries.put("resource-key", key);

        ArrayList<CityWithAverages> arr = new ArrayList<>();
        for(Pair<CityInfo, List<RecordedAverage>> pair : data.getData()) {
            arr.add(new CityWithAverages(pair.getLeft(), pair.getRight()));
        }

        entries.put("averages", arr);

        response.responseData = entries;
        response.errorData = null;

        return response;
    }

    public static ChartResponse createResponseDataOnly(ChartData data) {
        ChartResponse response = new ChartResponse();
        Map<String, Object> entries = new LinkedHashMap<>();

        ArrayList<CityWithAverages> arr = new ArrayList<>();
        for(Pair<CityInfo, List<RecordedAverage>> pair : data.getData()) {
            arr.add(new CityWithAverages(pair.getLeft(), pair.getRight()));
        }

        entries.put("averages", arr);
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


    private static class CityWithAverages {
        CityInfo city;
        List<RecordedAverage> averages;

        public CityWithAverages(CityInfo city, List<RecordedAverage> averages) {
            this.city = city;
            this.averages = averages;
        }
    }


}
