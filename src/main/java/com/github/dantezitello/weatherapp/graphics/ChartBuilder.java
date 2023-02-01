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

import com.github.dantezitello.weatherapp.common.UnitType;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.CategoryItemRenderer;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.chart.ui.RectangleInsets;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DatasetUtils;

import java.awt.*;
import java.text.DecimalFormat;

public class ChartBuilder {

    private CategoryLabelPositions position;
    private CategoryItemRenderer renderer;
    private DefaultCategoryDataset dataset;
    private UnitType type;

    private String title;
    private int seriesCount;

    private boolean compressRange;

    public ChartBuilder(String title) {
        this(title, UnitType.CELSIUS);
    }

    public ChartBuilder(String title, UnitType type) {
        this.type = type;
        this.title = title;
        this.seriesCount = 0;
        this.dataset = new DefaultCategoryDataset();
        this.compressRange = false;
        this.position = CategoryLabelPositions.STANDARD;
    }

    public ChartBuilder smallDataset() {
        position = CategoryLabelPositions.STANDARD;
        return this;
    }

    public ChartBuilder largeDataset() {
        position = CategoryLabelPositions.UP_45;
        return this;
    }

    public ChartBuilder hugeDataset() {
        position = CategoryLabelPositions.UP_90;
        return this;
    }

    public ChartCompletion barchart() {
        BarRenderer renderer = new BarRenderer();
        renderer.setItemMargin(0);
        renderer.setDrawBarOutline(true);
        renderer.setDefaultOutlinePaint(Color.BLACK);
        renderer.setDefaultOutlineStroke(new BasicStroke());
        renderer.setShadowVisible(false);
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultSeriesVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());

        this.renderer = renderer;


        return new ChartCompletion();
    }

    public ChartCompletion linechart() {
        LineAndShapeRenderer renderer = new LineAndShapeRenderer();
        renderer.setItemMargin(0);
        renderer.setDefaultOutlinePaint(Color.WHITE);
        renderer.setDefaultOutlineStroke(new BasicStroke());
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultSeriesVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultShapesVisible(true);


        this.renderer = renderer;
        this.compressRange = true;

        return new ChartCompletion();
    }

    public class ChartSeriesBuilder {
        private String seriesKey;

        public ChartSeriesBuilder(String seriesKey) {
            this.seriesKey = seriesKey;
        }

        public ChartSeriesBuilder addValue(Number value, Comparable key) {
            dataset.addValue(value, seriesKey, key);
            return this;
        }

        public ChartCompletion finish() {
            return new ChartCompletion();
        }
    }

    public class ChartCompletion {

        public ChartSeriesBuilder series(String seriesKey, Color seriesColor) {
            renderer.setSeriesPaint(seriesCount++, seriesColor);
            return new ChartSeriesBuilder(seriesKey);
        }

        public JFreeChart chart() {
            CategoryAxis categoryAxis = new CategoryAxis("Date");
            categoryAxis.setCategoryLabelPositions(position);


            NumberAxis valuesAxis = new NumberAxis("Temperature");
            String pattern = String.format("0.00 %s", type.getSymbol());

            valuesAxis.setNumberFormatOverride(new DecimalFormat(pattern));

            //We need to space out the data so it doesn't get crammed together
            if(compressRange) {
                valuesAxis.setLowerBound( ((Double) DatasetUtils.findMinimumRangeValue( dataset )) - 5.0d);
                valuesAxis.setUpperBound( ((Double) DatasetUtils.findMaximumRangeValue( dataset )) + 5.0d);
            }

            CategoryPlot plot = new CategoryPlot(dataset, categoryAxis, valuesAxis, renderer);
            plot.setOrientation(PlotOrientation.VERTICAL);
            plot.setInsets(new RectangleInsets(0,0,0,0)); //No insets
            plot.setBackgroundPaint(Color.WHITE); //White field background


            JFreeChart chart = new JFreeChart( title, JFreeChart.DEFAULT_TITLE_FONT, plot, true);
            chart.setBackgroundPaint(Color.WHITE); //white chart background

            return chart;
        }
    }



}
