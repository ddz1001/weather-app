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

package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.LocalDateRange;
import com.github.dantezitello.weatherapp.common.RecordedAverage;
import com.github.dantezitello.weatherapp.weather.model.DailyTemperatureData;
import com.github.dantezitello.weatherapp.weather.model.WeatherHistoryModel;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.IsoFields;
import java.time.temporal.TemporalField;
import java.util.LinkedList;
import java.util.List;

public class WeatherResultAggregation {


    final static TemporalField ISO_WEEK = IsoFields.WEEK_OF_WEEK_BASED_YEAR;

    public static WeatherHistoryResult aggregateDaily(WeatherHistoryModel model) {
        //Here we just directly map one to one since there are no intervals
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<RecordedAverage> entries = result.getWeatherEntries();

        for(int i = 0; i < dates.size(); i++) {
            entries.add( new RecordedAverage( LocalDateRange.of(dates.get(i)), temps.get(i), model.getUnitData().getUnitType() ) );
        }

        return result;
    }

    public static WeatherHistoryResult aggregateWeekly(WeatherHistoryModel model) {

        //We need to aggregate the weekly averages
        //The range should start on the first day of a week, and the end range should be on the final day of a week
        //Each interval will start on the first day of a week, and end on the last day of that week
        //Week starts and ends are defined by the ISO standard, and not by locale
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<RecordedAverage> entries = result.getWeatherEntries();



        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        int currentIsoWeek = start.get(ISO_WEEK);

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.get(ISO_WEEK) != currentIsoWeek ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, end ), avg, model.getUnitData().getUnitType() ) );

                currentIsoWeek = cur.get(ISO_WEEK);
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, cur ), avg, model.getUnitData().getUnitType() ) );

                break;
            }
            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }

    public static WeatherHistoryResult aggregateMonthly(WeatherHistoryModel model) {
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<RecordedAverage> entries = result.getWeatherEntries();

        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        Month currentMonth = start.getMonth();

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.getMonth() != currentMonth ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, end ), avg, model.getUnitData().getUnitType() ) );

                currentMonth = cur.getMonth();
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, cur ), avg, model.getUnitData().getUnitType() ) );

                break;
            }

            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }

    public static WeatherHistoryResult aggregateYearly(WeatherHistoryModel model) {

        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<RecordedAverage> entries = result.getWeatherEntries();

        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        int currentYear = start.getYear();

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.getYear() != currentYear ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, end ), avg, model.getUnitData().getUnitType() ) );

                currentYear = cur.getYear();
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new RecordedAverage( LocalDateRange.from( start, cur ), avg, model.getUnitData().getUnitType() ) );

                break;
            }
            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }



    private static class BigDecimalAccumulator {

        List<BigDecimal> decimals;


        public BigDecimalAccumulator() {
            this.decimals = new LinkedList<>();
        }


        public void add(BigDecimal value) {
            decimals.add(value);
        }

        public BigDecimal computeAverage() {
            BigDecimal total = new BigDecimal(decimals.size());
            BigDecimal sum = new BigDecimal(0);
            for(BigDecimal val : decimals) {
                sum = sum.add(val);
            }

            decimals.clear();

            return sum.divide(total, new MathContext(2, RoundingMode.CEILING));
        }
    }

}
