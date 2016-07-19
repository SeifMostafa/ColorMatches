package com.example.seifmostafa.colormatches;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;

import java.util.Random;
import java.util.Stack;

import static com.example.seifmostafa.colormatches.Utilities.*;

public class PlaygroundActivity extends AppCompatActivity {

    GridView gridView;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playground);
        gridView = (GridView)findViewById(R.id.gridview_playground);
         BaseAdapter mListAdapter = new BaseAdapter() {
             @Override
            public int getCount() {
                return 64;
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
                    imageButton.setBackgroundColor(Utilities.getColor());
                }
                view.findViewById(R.id.imageButton_color).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(PlaygroundActivity.this,"Color Selected",Toast.LENGTH_SHORT).show();
                    }
                });
                AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        gridView.getHeight()*4/getCount());
                view.setLayoutParams(param);
                return  view;
            }
        };
        gridView.setAdapter(mListAdapter);
        gridView.setNumColumns(4);
    }

}


