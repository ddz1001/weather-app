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

package com.github.dantezitello.weatherapp.graphics.renderers;

import org.jfree.chart.ChartHints;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.renderer.category.CategoryItemRendererState;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.CategoryDataset;
import org.jfree.svg.SVGHints;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Map;

public class TitledLineAndShapeRenderer extends LineAndShapeRenderer {


    private final Color ALPHA = new Color(0,0,0,0);

    public TitledLineAndShapeRenderer() {
        super();
    }

    public TitledLineAndShapeRenderer(boolean lines, boolean shapes) {
        super(lines, shapes);
    }

    @Override
    public void drawItem(Graphics2D g2, CategoryItemRendererState state, Rectangle2D dataArea, CategoryPlot plot, CategoryAxis domainAxis, ValueAxis rangeAxis, CategoryDataset dataset, int row, int column, int pass) {

        if(pass == 1) { //shape pass

            if (!getItemVisible(row, column)) {
                return;
            }

            // do nothing if both the line and shape are not visible
            if (!getItemLineVisible(row, column)
                    && !getItemShapeVisible(row, column)) {
                return;
            }

            // nothing is drawn for null...
            Number v = dataset.getValue(row, column);
            if (v == null) {
                return;
            }

            g2.setRenderingHint(ChartHints.KEY_BEGIN_ELEMENT, Map.of("ref", "titled"));
            g2.setRenderingHint(SVGHints.KEY_ELEMENT_TITLE, v.toString());
            super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);


            g2.setRenderingHint(ChartHints.KEY_END_ELEMENT, true );
        }
        else {
            super.drawItem(g2, state, dataArea, plot, domainAxis, rangeAxis, dataset, row, column, pass);
        }
    }
}
