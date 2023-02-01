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
