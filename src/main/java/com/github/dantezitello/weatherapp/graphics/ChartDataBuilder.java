package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.CityInfo;
import com.github.dantezitello.weatherapp.common.RecordedAverage;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;

public class ChartDataBuilder {

    private List<Pair<CityInfo, List<RecordedAverage>>> data;

    public ChartDataBuilder() {
        data = new ArrayList<>();
    }

    public ChartDataBuilder addInfo(CityInfo cityInfo, RecordedAverage... averages) {
        data.add( new ImmutablePair<>(cityInfo, new ArrayList<>(List.of(averages)))  );
        return this;
    }

    public ChartDataBuilder addInfo(CityInfo cityInfo, List<RecordedAverage> averages) {
        data.add( new ImmutablePair<>(cityInfo, new ArrayList<>(averages)));
        return this;
    }

    public ChartData build() {
        ChartData chart = new ChartData();
        chart.getData().addAll(data);

        return chart;
    }

}
