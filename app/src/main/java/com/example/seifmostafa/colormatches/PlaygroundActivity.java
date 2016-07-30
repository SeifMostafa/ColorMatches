package com.example.seifmostafa.colormatches;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

public class PlaygroundActivity extends AppCompatActivity {

    GridView gridView;
    ImageButton imageButton;
    /// set control , initialize by 4 NOW
    int numcol=4,Waitedid;
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
        public View getView(int i, View view, final ViewGroup viewGroup) {
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
                         //  Toast.makeText(PlaygroundActivity.this,"Gun ++, "+ String.valueOf(view.findViewById(R.id.imageButton_color).getTag()),Toast.LENGTH_SHORT).show();
                           int[][]NEWCOLORS= Controller.MatchesAction((Integer) view.findViewById(R.id.imageButton_color).getTag(),colors);
                           Log.i("MatchesAction",String.valueOf(NEWCOLORS[0][1])
                                   +String.valueOf(NEWCOLORS[0][0])+String.valueOf(NEWCOLORS[1][0])+String.valueOf(NEWCOLORS[1][1]));
                         //  Controller.showGuns();
                           view.setBackgroundColor(NEWCOLORS[0][0]);
                           view.setTag(NEWCOLORS[0][1]);
                          view.setClickable(true);
                          ImageButton oldview = (ImageButton) viewGroup.findViewById(Waitedid);
                           oldview.setBackgroundColor(NEWCOLORS[1][0]);
                           oldview.setTag(NEWCOLORS[1][1]);
                           oldview.setClickable(true);
                           click=false;
                           notifyDataSetChanged();
                           waitedcolorid=null;
                       }
                        else
                       {
                           ImageButton oldview = (ImageButton) viewGroup.findViewById(Waitedid);
                           oldview.setClickable(true);
                           waitedcolorid=  view.findViewById(R.id.imageButton_color).getTag();
                           view.findViewById(R.id.imageButton_color).setClickable(false);
                           Waitedid=view.getId();

                       }
                    }
                    else
                    {
                        click=true;
                        waitedcolorid=  view.findViewById(R.id.imageButton_color).getTag();
                        view.findViewById(R.id.imageButton_color).setClickable(false);
                        Waitedid=view.getId();
                        Log.i("VerifyingVIEWID",String.valueOf(viewGroup.findViewById(Waitedid).getTag()));
                    }
                }
            });
            AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    gridView.getHeight()*numCol/getCount());
            view.setLayoutParams(param);

            return  view;
        }

        @Override
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
        }

    }
    public boolean Auth2ndColor(Object color)
    {
        Log.i("Auth2ndColor",color+" --$$$--  "+waitedcolorid.toString());
        if(waitedcolorid!=null)
        {
            if(color.equals(waitedcolorid))return true;
            else return false;
        }
        else return false;
    }
}


