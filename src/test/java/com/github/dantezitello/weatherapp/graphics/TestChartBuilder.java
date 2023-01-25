package com.github.dantezitello.weatherapp.graphics;

import com.github.dantezitello.weatherapp.weather.model.UnitType;
import org.jfree.chart.JFreeChart;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;

public class TestChartBuilder {


    @Test
    public void testBarChart1() throws IOException {


        ChartBuilder builder = new ChartBuilder("Pittsburgh, PA; USA", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.barchart()
                .series("Recorded High", Color.DARK_GRAY)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("barchartA.svg"), svg);
    }

    @Test
    public void testLineChart() throws IOException {


        ChartBuilder builder = new ChartBuilder("Pittsburgh, PA; USA", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.linechart()
                .series("Recorded High", Color.DARK_GRAY)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("linechartA.svg"), svg);
    }



    @Test
    public void testBarChartSideBySide() throws IOException {


        ChartBuilder builder = new ChartBuilder("Comparison", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.barchart()
                .series("Pittsburgh, PA; USA", Color.WHITE)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .series("Atlanta, GA; USA", Color.LIGHT_GRAY)
                .addValue(new BigDecimal("59.1"), "March 1st")
                .addValue(new BigDecimal("62.2"), "March 2nd")
                .addValue(new BigDecimal("64.4"), "March 3rd")
                .addValue(new BigDecimal("67.1"), "March 4th")
                .addValue(new BigDecimal("63.1"), "March 5th")
                .addValue(new BigDecimal("64.3"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("barchartB.svg"), svg);
    }


    @Test
    public void testLineChartTwoSeries() throws IOException {


        ChartBuilder builder = new ChartBuilder("Comparison", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.linechart()
                .series("Pittsburgh, PA; USA", Color.BLACK)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .series("Atlanta, GA; USA", Color.LIGHT_GRAY)
                .addValue(new BigDecimal("59.1"), "March 1st")
                .addValue(new BigDecimal("62.2"), "March 2nd")
                .addValue(new BigDecimal("64.4"), "March 3rd")
                .addValue(new BigDecimal("67.1"), "March 4th")
                .addValue(new BigDecimal("63.1"), "March 5th")
                .addValue(new BigDecimal("64.3"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("linechartB.svg"), svg);
    }

    @Test
    public void testBarChartMultiple() throws IOException {


        ChartBuilder builder = new ChartBuilder("Comparison", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.barchart()
                .series("Pittsburgh, PA; USA", Color.WHITE)
                    .addValue(new BigDecimal("55.1"), "March 1st")
                    .addValue(new BigDecimal("56.2"), "March 2nd")
                    .addValue(new BigDecimal("58.9"), "March 3rd")
                    .addValue(new BigDecimal("61.2"), "March 4th")
                    .addValue(new BigDecimal("60.2"), "March 5th")
                    .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .series("Atlanta, GA; USA", Color.LIGHT_GRAY)
                    .addValue(new BigDecimal("59.1"), "March 1st")
                    .addValue(new BigDecimal("62.2"), "March 2nd")
                    .addValue(new BigDecimal("64.4"), "March 3rd")
                    .addValue(new BigDecimal("67.1"), "March 4th")
                    .addValue(new BigDecimal("63.1"), "March 5th")
                    .addValue(new BigDecimal("64.3"), "March 6th")
                .finish()
                .series("Cairo, EGY", Color.DARK_GRAY)
                    .addValue(new BigDecimal("68.1"), "March 1st")
                    .addValue(new BigDecimal("72.2"), "March 2nd")
                    .addValue(new BigDecimal("74.4"), "March 3rd")
                    .addValue(new BigDecimal("74.2"), "March 4th")
                    .addValue(new BigDecimal("73.1"), "March 5th")
                    .addValue(new BigDecimal("70.7"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("barchartC.svg"), svg);
    }


    @Test
    public void testLineChartMultiple() throws IOException {


        ChartBuilder builder = new ChartBuilder("Comparison", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.linechart()
                .series("Pittsburgh, PA; USA", Color.RED)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .series("Atlanta, GA; USA", Color.BLUE)
                .addValue(new BigDecimal("59.1"), "March 1st")
                .addValue(new BigDecimal("62.2"), "March 2nd")
                .addValue(new BigDecimal("64.4"), "March 3rd")
                .addValue(new BigDecimal("67.1"), "March 4th")
                .addValue(new BigDecimal("63.1"), "March 5th")
                .addValue(new BigDecimal("64.3"), "March 6th")
                .finish()
                .series("Cairo, EGY", Color.GREEN)
                .addValue(new BigDecimal("68.1"), "March 1st")
                .addValue(new BigDecimal("72.2"), "March 2nd")
                .addValue(new BigDecimal("74.4"), "March 3rd")
                .addValue(new BigDecimal("74.2"), "March 4th")
                .addValue(new BigDecimal("73.1"), "March 5th")
                .addValue(new BigDecimal("70.7"), "March 6th")
                .finish()
                .chart();

        String svg = ChartGraphicsUtils.convertToSvg(chart, new Dimension(600, 400));

        Files.writeString(Path.of("linechartC.svg"), svg);
    }



    @Test
    public void testBarChartToImage() throws IOException {


        ChartBuilder builder = new ChartBuilder("Pittsburgh, PA; USA", UnitType.FAHRENHEIT);
        JFreeChart chart = builder.barchart()
                .series("Recorded High", Color.DARK_GRAY)
                .addValue(new BigDecimal("55.1"), "March 1st")
                .addValue(new BigDecimal("56.2"), "March 2nd")
                .addValue(new BigDecimal("58.9"), "March 3rd")
                .addValue(new BigDecimal("61.2"), "March 4th")
                .addValue(new BigDecimal("60.2"), "March 5th")
                .addValue(new BigDecimal("59.1"), "March 6th")
                .finish()
                .chart();

        BufferedImage image = ChartGraphicsUtils.convertToBufferedImage(chart, new Dimension(600, 400));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ImageIO.write(image, "png", out);
        out.flush();

        Files.write(Path.of("barchart.png"), out.toByteArray());

    }


}
