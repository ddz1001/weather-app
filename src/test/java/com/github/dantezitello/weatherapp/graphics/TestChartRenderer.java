package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.common.*;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.common.administration.UnitedStatesState;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TestChartRenderer {

    @Test
    public void testRendererDaily() throws IOException {

        ChartOptions options = new ChartOptions(ChartStyling.BAR, Interval.DAILY, ContentGenerationType.PNG, UnitType.FAHRENHEIT);
        byte[] bytes = ChartRenderer.renderChart(buildTestDataDaily(), options);

        Files.write(Path.of("src/test/resources/output.png"), bytes);
    }

    @Test
    public void testRendererBig() throws IOException {

        ChartOptions options = new ChartOptions(ChartStyling.LINE, Interval.DAILY, ContentGenerationType.PNG, UnitType.FAHRENHEIT);
        byte[] bytes = ChartRenderer.renderChart(longSeries(), options);

        Files.write(Path.of("src/test/resources/big.png"), bytes);
    }



    public ChartData buildTestDataDaily() {
        ChartDataBuilder builder = new ChartDataBuilder();


        CityInfo newyork = new CityInfo("New York City", Country.UNITED_STATES, UnitedStatesState.NEW_YORK.englishName());
        CityInfo losangeles = new CityInfo("Los Angeles", Country.UNITED_STATES,UnitedStatesState.CALIFORNIA.englishName());

        List<RecordedAverage> nyAverages = List.of(
                RecordedAverage.record("10.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 1)),
                RecordedAverage.record("11.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 2)),
                RecordedAverage.record("12.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 3)),
                RecordedAverage.record("13.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 4)),
                RecordedAverage.record("12.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 5)),
                RecordedAverage.record("11.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 6)),
                RecordedAverage.record("13.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 7))
        );

        List<RecordedAverage> laAverages = List.of(
                RecordedAverage.record("100.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 1)),
                RecordedAverage.record("101.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 2)),
                RecordedAverage.record("102.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 3)),
                RecordedAverage.record("102.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 4)),
                RecordedAverage.record("102.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 5)),
                RecordedAverage.record("101.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 6)),
                RecordedAverage.record("103.5", UnitType.FAHRENHEIT, LocalDate.of(2019, 10, 7))
        );

        builder.addInfo(newyork, nyAverages);
        builder.addInfo(losangeles, laAverages);

        return builder.build();
    }

    public ChartData longSeries() {

        ChartDataBuilder builder = new ChartDataBuilder();


        CityInfo newyork = new CityInfo("New York City", Country.UNITED_STATES, UnitedStatesState.NEW_YORK.englishName());

        LocalDate currentDate = LocalDate.of(2020,1,1);

        List<RecordedAverage> averages = new ArrayList<>();
        for(int i = 0; i < 100 ; i++) {
            averages.add(RecordedAverage.record("10", UnitType.CELSIUS, currentDate));
            currentDate = currentDate.plusDays(1);
        }

        builder.addInfo(newyork, averages);

        return builder.build();
    }
}
