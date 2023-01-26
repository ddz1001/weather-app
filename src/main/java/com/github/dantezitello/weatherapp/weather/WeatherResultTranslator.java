package com.github.dantezitello.weatherapp.weather;

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

public class WeatherResultTranslator {


    final TemporalField ISO_WEEK = IsoFields.WEEK_OF_WEEK_BASED_YEAR;

    public WeatherHistoryResult translateDaily(WeatherHistoryModel model) {
        //Here we just directly map one to one since there are no intervals
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<WeatherEntry> entries = result.getWeatherEntries();

        for(int i = 0; i < dates.size(); i++) {
            entries.add( new WeatherEntry( WeatherInterval.of(dates.get(i)), temps.get(i) ) );
        }

        return result;
    }

    public WeatherHistoryResult translateWeekly(WeatherHistoryModel model) {

        //We need to aggregate the weekly averages
        //The range should start on the first day of a week, and the end range should be on the final day of a week
        //Each interval will start on the first day of a week, and end on the last day of that week
        //Week starts and ends are defined by the ISO standard, and not by locale
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<WeatherEntry> entries = result.getWeatherEntries();



        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        int currentIsoWeek = start.get(ISO_WEEK);

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.get(ISO_WEEK) != currentIsoWeek ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, end ), avg ) );

                currentIsoWeek = cur.get(ISO_WEEK);
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, cur ), avg ) );

                break;
            }
            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }

    public WeatherHistoryResult translateMonthly(WeatherHistoryModel model) {
        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<WeatherEntry> entries = result.getWeatherEntries();

        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        Month currentMonth = start.getMonth();

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.getMonth() != currentMonth ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, end ), avg ) );

                currentMonth = cur.getMonth();
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, cur ), avg ) );

                break;
            }

            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }

    public WeatherHistoryResult translateYearly(WeatherHistoryModel model) {

        WeatherHistoryResult result = new WeatherHistoryResult();
        DailyTemperatureData data = model.getDailyTemperatureData();
        List<LocalDate> dates = data.getTimeEntries();
        List<BigDecimal> temps = data.getTemperatureEntries();
        List<WeatherEntry> entries = result.getWeatherEntries();

        LocalDate start = dates.get(0);
        LocalDate end = dates.get(0);
        int currentYear = start.getYear();

        BigDecimalAccumulator accumulator = new BigDecimalAccumulator();

        for(int i = 0; i < dates.size(); i++) {
            LocalDate cur = dates.get(i);
            if( cur.getYear() != currentYear ) {
                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, end ), avg ) );

                currentYear = cur.getYear();
                start = cur; end = cur;
            }
            else if( i == dates.size() - 1 ) {
                accumulator.add(temps.get(i));

                BigDecimal avg = accumulator.computeAverage();
                entries.add(new WeatherEntry( WeatherInterval.from( start, cur ), avg ) );

                break;
            }
            else {
                end = cur;
            }

            accumulator.add( temps.get(i) );
        }

        return result;
    }



    private class BigDecimalAccumulator {

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
