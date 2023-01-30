package com.github.dantezitello.weatherapp.graphics;

import java.awt.*;
import java.util.Random;

public class ColorUtils {

    static final Color[] COLORS = {
            Color.BLUE,
            Color.BLACK,
            Color.CYAN,
            Color.GREEN,
            Color.LIGHT_GRAY,
            Color.YELLOW
    };


    public static Color getForSeriesIndex(int index) {
        if(index > COLORS.length) {
            //pick a random color
            Random random = new Random();
            int r,g,b;
            r = random.nextInt(255); g = random.nextInt(255); b = random.nextInt(255);
            return new Color(r,g,b);
        }
        else {
            return COLORS[index];
        }
    }



}
