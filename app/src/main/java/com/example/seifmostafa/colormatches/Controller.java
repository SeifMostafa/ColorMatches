package com.example.seifmostafa.colormatches;

import android.graphics.Color;
import android.util.Log;

import java.sql.Struct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
    public static int[][] MatchesAction(int IndexofColorInResourses, int[][] colors)
    {
        GUNS[IndexofColorInResourses]++;
        String NEW_FROMOLD[]= new String[2];
        //2d ArrayList
        List<Integer> list = copy2dinto1d(colors);
        // remove the selected color from colors
        List<Integer>   C =  new ArrayList<>();
                C =removeSelectedColor(IndexofColorInResourses,list);
        // retrive all resources into a copy of and remove all colors from
            // remove one by one ( copy colors into another one and remove one by one)
        List<String> R = new ArrayList<>();
        Collections.addAll(R, Utilities.Resources);
        R = RemovefromR(C,R);
        // generate Randomly color from colors
        Random randomGenerator = new Random(); // Construct a new Random number generator
        int randomNumber = randomGenerator.nextInt(C.size()-1);
        NEW_FROMOLD[0]=Utilities.Resources[C.get(randomNumber)];
        // generate Randomly color from copy of resources
         randomNumber = randomGenerator.nextInt(R.size()-1);
        NEW_FROMOLD[1]=R.get(randomNumber);
        int [][]ColorsAsInt = new int[NEW_FROMOLD.length][2];
        for(int i=0;i<NEW_FROMOLD.length;i++)
        {
            String color=NEW_FROMOLD[i];
            int colorAsInt = Color.parseColor(color);
            ColorsAsInt[i][0]=colorAsInt;
            ColorsAsInt[i][1]=Utilities.getIndexFromResources(color);
        }
        return ColorsAsInt;
    }
    public static void showGuns() {
        for(int i=0;i<GUNS.length;i++)
        {
            Log.i("ShowGuns",String.valueOf(GUNS[i]));
        }
    }

    public static List<Integer> copy2dinto1d(int[][] colors){
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<colors.length;i++)
        {
            list.add(colors[i][1]);
        }
        return list;
    }
    public static List<Integer>removeSelectedColor(int IndexofColorInResourses, List<Integer> list)
    {
        for(int i=0;i<list.size();i++)
        {
            if(IndexofColorInResourses==list.get(i))
                list.remove(i);
        }
        return list;
    }
    public static  List<String>RemovefromR( List<Integer> C, List<String> R)
    {
        for (int i=0;i<C.size();i++)
        {
            R.remove(C.get(i));
        }
        return R;
    }
}
