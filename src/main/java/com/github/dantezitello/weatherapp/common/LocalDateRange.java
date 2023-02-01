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

import java.time.LocalDate;

public class LocalDateRange {

    private LocalDate start;
    private LocalDate end;

    private LocalDateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }


    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }


    public boolean isSingle() {
        return start == end;
    }

    public static LocalDateRange of(LocalDate date) {
        return new LocalDateRange(date, date);
    }

    public static LocalDateRange from(LocalDate start, LocalDate end) {
        return new LocalDateRange(start, end);
    }

}


