package com.example.seifmostafa.colormatches;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by seifmostafa on 16/07/16.
 */
public  class Utilities {

        public static String[] Resources = {
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
        public static int[][] getColors(String[] mColors) {
            int [][]ColorsAsInt = new int[mColors.length][2];
            List<String> list = new ArrayList<>();
            Collections.addAll(list, mColors);
            // Randomly select a fact
            for(int i=0;i<mColors.length;i++)
            {
                String color = null;
                Random randomGenerator = new Random(); // Construct a new Random number generator
                int randomNumber = randomGenerator.nextInt(list.size());
                color = list.get(randomNumber);
                list.remove(randomNumber);
                int colorAsInt = Color.parseColor(color);
                ColorsAsInt[i][0]=colorAsInt;
                ColorsAsInt[i][1]=getIndexFromResources(color);
            }
            return ColorsAsInt;
        }
    /// handle -1 case
    public static int getIndexFromResources(String chosen)
    {
        for(int i=0;i<Resources.length;i++)
        {
            if(chosen==Resources[i])
                return i;
        }
        return -1;
    }
}
