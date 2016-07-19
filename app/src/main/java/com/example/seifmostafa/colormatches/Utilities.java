package com.example.seifmostafa.colormatches;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.Random;

/**
 * Created by seifmostafa on 16/07/16.
 */
public  class Utilities {

        public static String[] mColors = {
                "#39add1", // light blue
                "#3079ab", // dark blue
                "#c25975", // mauve
                "#e15258", // red
                "#f9845b", // orange
                "#838cc7", // lavender
                "#7d669e", // purple
                "#53bbb4", // aqua
                "#51b46d", // green
                "#e0ab18", // mustard
                "#637a91", // dark gray
                "#f092b0", // pink
                "#b7c0c7"  // light gray
        };

        // Method (abilities: things the object can do)
        public static int getColor() {
            String color = null;

            // Randomly select a fact
            Random randomGenerator = new Random(); // Construct a new Random number generator
            int randomNumber = randomGenerator.nextInt(mColors.length);

            color = mColors[randomNumber];
            int colorAsInt = Color.parseColor(color);

            return colorAsInt;
        }
}
