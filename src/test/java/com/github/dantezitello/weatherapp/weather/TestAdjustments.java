package com.github.dantezitello.weatherapp.weather;

import com.github.dantezitello.weatherapp.common.LocalDateRange;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestAdjustments {



    @Test
    public void testWeekEnd() {

        LocalDate date = LocalDate.parse("2023-01-23");
        LocalDate adjusted = LocalDateAdjustments.adjustToWeekEnd(date);

        LocalDate expected = LocalDate.parse("2023-01-29");

        assertEquals(expected, adjusted);
    }

    @Test
    public void testWeekStart() {

        LocalDate date = LocalDate.parse("2023-01-29");
        LocalDate adjusted = LocalDateAdjustments.adjustToWeekStart(date);

        LocalDate expected = LocalDate.parse("2023-01-23");

        assertEquals(expected, adjusted);
    }


    @Test
    public void testYearSplit1() {
        List<LocalDateRange> ranges = LocalDateAdjustments.splitYearlyIntoRanges(2019, 2019);

        assertEquals(1, ranges.size());
    }

    @Test
    public void testYearSplit2() {
        List<LocalDateRange> ranges = LocalDateAdjustments.splitYearlyIntoRanges(1965, 2005);

        assertEquals(41, ranges.size());
    }

}
