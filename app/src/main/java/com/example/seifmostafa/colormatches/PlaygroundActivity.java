package com.example.seifmostafa.colormatches;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Random;
import java.util.Stack;

import static com.example.seifmostafa.colormatches.Utilities.*;

public class PlaygroundActivity extends AppCompatActivity {

    GridView gridView;
    ImageButton imageButton;
    /// set control , initialize by 4 NOW
    int numcol=4;
    int totalblocks=16;
    Object waitedcolorid=null;
    boolean click=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        gridView = (GridView)findViewById(R.id.gridview_playground);
        ListAdapter4Gridview mListAdapter = new ListAdapter4Gridview();
        mListAdapter.setsize(totalblocks,numcol);
        mListAdapter.setColors(Utilities.getColors(new Controller().build(totalblocks,Utilities.Resources)));
        gridView.setAdapter(mListAdapter);
        gridView.setNumColumns(numcol);
    }
    public class ListAdapter4Gridview extends BaseAdapter {
        int level,numCol,colors[][];
        public void setsize(int i,int j)
        {
            this.level=i;
            this.numCol=j;
        }
        public void setColors(int [][]mColors)
        {
            this.colors=mColors;
        }
        @Override
        public int getCount() {
            return level;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return i+1;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            if (view == null) {
                view = getLayoutInflater().inflate(R.layout.layout_color, viewGroup, false);
                imageButton = (ImageButton) view.findViewById(R.id.imageButton_color);
                imageButton.setBackgroundColor(colors[i][0]);
                imageButton.setTag(colors[i][1]);
            }
            view.findViewById(R.id.imageButton_color).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(click)
                    {
                       if(Auth2ndColor(view.findViewById(R.id.imageButton_color).getTag()))
                       {
                           Toast.makeText(PlaygroundActivity.this,"Gun ++, "+ String.valueOf(view.findViewById(R.id.imageButton_color).getTag()),Toast.LENGTH_SHORT).show();
                            Controller.MatchesAction((Integer) view.findViewById(R.id.imageButton_color).getTag());
                           Controller.showGuns();
                       }
                        else
                       {
                           Toast.makeText(PlaygroundActivity.this,"Doesn't match",Toast.LENGTH_SHORT).show();
                       }
                        click=false;
                        waitedcolorid=null;
                    }
                    else
                    {
                        click=true;
                        waitedcolorid=  view.findViewById(R.id.imageButton_color).getTag();
                    }
                }
            });
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    gridView.getHeight()*numCol/getCount());
            view.setLayoutParams(param);
            return  view;
        }
    }
    public boolean Auth2ndColor(Object color)
    {
        Log.i("Auth2ndColor",color+" --$$$--  "+waitedcolorid);
        if(color.equals(waitedcolorid))return true;
        else return false;
    }

}


