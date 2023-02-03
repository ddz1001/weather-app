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

import com.github.dantezitello.weatherapp.common.*;
import org.apache.commons.lang3.tuple.Pair;
import org.jfree.chart.JFreeChart;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.time.temporal.IsoFields;
import java.util.List;
import java.util.Locale;
import java.util.function.Function;

public class ChartRenderer {


    static final Function<LocalDateRange, String> DAILY_FMT = (ldr) -> ldr.getStart().format(DateTimeFormatter.ISO_LOCAL_DATE);
    static final Function<LocalDateRange, String> WEEKLY_FMT = (ldr) -> String.format("W%d, %d", ldr.getStart().get(IsoFields.WEEK_OF_WEEK_BASED_YEAR), ldr.getStart().get(IsoFields.WEEK_BASED_YEAR) );
    static final Function<LocalDateRange, String> MONTHLY_FMT = (ldr) -> String.format("%s, %d", ldr.getStart().getMonth().getDisplayName(TextStyle.FULL, Locale.US), ldr.getStart().getYear());
    static final Function<LocalDateRange, String> YEARLY_FMT = (ldr) -> String.format("%d", ldr.getStart().getYear());

    public static byte[] renderChart(ChartData data, ChartOptions options) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            renderChart(data, options, outputStream);

            return outputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void renderChart(ChartData data, ChartOptions options, OutputStream out) throws IOException {
        String titleFmt = "%s Recorded Averages";
        String title = "";

        Function<LocalDateRange, String> fmt = null;


        switch(options.getIntervalType()) {

            case DAILY -> {
                title = String.format(titleFmt, "Daily");
                fmt = DAILY_FMT;
            }
            case WEEKLY -> {
                title = String.format(titleFmt, "Weekly");
                fmt = WEEKLY_FMT;
            }
            case MONTHLY -> {
                title = String.format(titleFmt, "Monthly");
                fmt = MONTHLY_FMT;
            }
            case YEARLY -> {
                title = String.format(titleFmt, "Yearly");
                fmt = YEARLY_FMT;
            }
        }


        ChartBuilder builder = new ChartBuilder(title, options.getUnitType());


        if(count(data) > 15 || options.getIntervalType() == Interval.MONTHLY) {
            //Months tend to get squished together, so we do it for them as well
            builder.largeDataset();
        }
        if(count(data) > 45) {
            builder.hugeDataset();
        }


        JFreeChart chart = null;
        if(options.getDisplayType() == ChartStyling.LINE) {
            chart = render(data, builder.linechart(), fmt);
        }
        else {
            chart = render(data, builder.barchart(), fmt);
        }

        Dimension size = idealDimension(data, options.getDisplayType());

        if(options.getContentType() == ContentGenerationType.SVG) {
            out.write( ChartGraphicsUtils.convertToSvg( chart, size ).getBytes(StandardCharsets.UTF_8) );
        }
        else {
            BufferedImage image = ChartGraphicsUtils.convertToBufferedImage( chart, size );
            ImageIO.write(image, "png", out);
        }
    }

    private static JFreeChart render(ChartData data, ChartBuilder.ChartCompletion builder, Function<LocalDateRange, String> keyFormatter) {
        int i = 0;
        for(Pair<CityInfo, List<RecordedAverage>> entry : data.getData()) {
            Color color = ColorUtils.getForSeriesIndex(i);
            ChartBuilder.ChartSeriesBuilder series = builder.series( formatCityName(entry.getLeft()), color );

            for(RecordedAverage average : entry.getRight()) {
                series.addValue(average.getTemperature(), keyFormatter.apply( average.getInterval() ) );
            }
            series.finish();
            i++;
        }

        return builder.chart();
    }

    private static String formatCityName(CityInfo info) {
        return String.format("%s, %s %s", info.getCityName(), info.getAdministrativeRegion(), info.getCountry().getTwoLetterCode());
    }

    private static Dimension idealDimension(ChartData data, ChartStyling styling) {
        int columnFactor = 1;
        int columnPx;

        int widthFactor = 1;
        int widthPx;

        if(styling == ChartStyling.BAR) {
            columnPx = 70;
            widthPx = 50;
        }
        else {
            columnPx = 25;
            widthPx = 10;
        }

        if(data.getData().size() > 2) {
            columnFactor = data.getData().size();
        }

        if(data.getData().get(0).getRight().size() > 12) {
            widthFactor = data.getData().get(0).getRight().size();
        }

        int extra = (columnFactor * columnPx) + (widthFactor * widthPx);

        return new Dimension(1000 + extra, 500);
    }

    private static int count(ChartData data) {
        return data.getData().get(0).getRight().size();
    }
}
