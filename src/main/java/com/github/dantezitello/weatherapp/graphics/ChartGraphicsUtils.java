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
