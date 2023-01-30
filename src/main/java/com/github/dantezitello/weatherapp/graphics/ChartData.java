package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.RecordedAverage;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ChartData {

    List<Pair<CityInfo, List<RecordedAverage>>> data;

    public ChartData() {
        data = new ArrayList<>();
    }

    public List<Pair<CityInfo, List<RecordedAverage>>> getData() {
        return data;
    }
}
