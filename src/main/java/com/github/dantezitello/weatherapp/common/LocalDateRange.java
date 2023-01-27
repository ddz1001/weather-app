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


