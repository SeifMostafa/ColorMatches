package com.example.seifmostafa.colormatches;

import android.util.Log;

import java.util.Random;

/**
 * Created by seifmostafa on 20/07/16.
 */
public class Controller {
    static int []GUNS=new int[Utilities.Resources.length];
    public String[] build(int totalblocks,String resources[]) {
        String result[]= new String[totalblocks];
        for(int i=0;i<totalblocks/2;i++)
        {
            String color = null;

            // Randomly select a fact
            Random randomGenerator = new Random(); // Construct a new Random number generator
            int randomNumber = randomGenerator.nextInt(resources.length);

            color = resources[randomNumber];
            result[i]=color;
            result[totalblocks-1-i]=color;
        }
        return result;
    }
    public static void MatchesAction(int IndexofColorInResourses)
    {
        GUNS[IndexofColorInResourses]++;
    }
    public static void showGuns()
    {
        for(int i=0;i<GUNS.length;i++)
        {
            Log.i("ShowGuns",String.valueOf(GUNS[i]));
        }
    }
}
