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

import org.jfree.chart.JFreeChart;
import org.jfree.svg.SVGGraphics2D;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class ChartGraphicsUtils {

    public static String convertToSvg(JFreeChart chart, Dimension size) {

        final SVGGraphics2D svg2d = new SVGGraphics2D(size.width, size.height);
        chart.draw(svg2d,new Rectangle2D.Double(0, 0, size.width, size.height));
        svg2d.dispose();

        return svg2d.getSVGElement();
    }

    public static BufferedImage convertToBufferedImage(JFreeChart chart, Dimension size) {
        BufferedImage bi = new BufferedImage(size.width, size.height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D imgGraphics = bi.createGraphics();

        chart.draw(imgGraphics, new Rectangle2D.Double(0,0, size.width, size.height));
        imgGraphics.dispose();

        return bi;
    }




}
