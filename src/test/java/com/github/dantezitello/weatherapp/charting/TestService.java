package com.github.dantezitello.weatherapp.charting;

import com.github.dantezitello.weatherapp.common.*;
import com.github.dantezitello.weatherapp.common.administration.Country;
import com.github.dantezitello.weatherapp.common.administration.UnitedStatesState;
import com.github.dantezitello.weatherapp.graphics.ChartData;
import com.github.dantezitello.weatherapp.graphics.ChartDataBuilder;
import com.github.dantezitello.weatherapp.graphics.ChartOptions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TestService {

    @Autowired
    ChartingService service;


    @Test
    public void testRendererDaily() {

        ChartOptions options = new ChartOptions(ChartStyling.BAR, Interval.DAILY, ContentGenerationType.SVG, UnitType.FAHRENHEIT);


        String key = service.createChart(buildTestDataDaily(), options);

        RenderedChartEntity entity = service.fetchFromKey(key);
        assertNotNull(entity);

    }



    public ChartData buildTestDataDaily() {
        ChartDataBuilder builder = new ChartDataBuilder();


        CityInfo newyork = new CityInfo("New York City", Country.UNITED_STATES,  UnitedStatesState.NEW_YORK.englishName());
        CityInfo losangeles = new CityInfo("Los Angeles", Country.UNITED_STATES, UnitedStatesState.CALIFORNIA.englishName());

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

}
