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
