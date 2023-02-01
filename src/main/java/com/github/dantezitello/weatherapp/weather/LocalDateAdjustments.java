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

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

public class LocalDateAdjustments {


    public static LocalDate adjustToWeekEnd(LocalDate date) {
        //ISO defines end of a week of the year to be a Sunday
        DayOfWeek currentDay = date.getDayOfWeek();
        if(currentDay != DayOfWeek.SUNDAY) {
            int days = DayOfWeek.SUNDAY.getValue() - currentDay.getValue();
            return date.plusDays(days);
        }
        else {
            return date;
        }
    }

    public static LocalDate adjustToWeekStart(LocalDate date) {
        //ISO defines start of a week of the year to be a Monday
        DayOfWeek currentDay = date.getDayOfWeek();
        if(currentDay != DayOfWeek.MONDAY) {
            int days = currentDay.getValue() - DayOfWeek.MONDAY.getValue();
            return date.minusDays(days);
        }
        else {
            return date;
        }
    }

    public static LocalDate adjustToMonthStart(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfMonth());
    }

    public static LocalDate adjustToMonthEnd(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfMonth());
    }

    public static LocalDate adjustToYearStart(LocalDate date) {
        return date.with(TemporalAdjusters.firstDayOfYear());
    }

    public static LocalDate adjustToYearEnd(LocalDate date) {
        return date.with(TemporalAdjusters.lastDayOfYear());
    }

    public static List<LocalDateRange> splitYearlyIntoRanges(int startYear, int endYear) {
        List<LocalDateRange> years = new ArrayList<>();

        for( ; startYear <= endYear ; startYear++) {

            LocalDate start = LocalDate.of(startYear, 1, 1);
            LocalDate end = LocalDate.of(startYear, 1, 1).with(TemporalAdjusters.lastDayOfYear());

            years.add(LocalDateRange.from(start, end));
        }

        return years;
    }

}
