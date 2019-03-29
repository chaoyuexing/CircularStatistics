package com.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Circle mCircle;
    private ArrayList<Integer> baifenbi = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mCircle = findViewById(R.id.circle_view);
        baifenbi.add(10);
        baifenbi.add(20);
        baifenbi.add(30);
        baifenbi.add(40);
        colors.add(Color.RED);
        colors.add(Color.YELLOW);
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        names.add("已完成");
        names.add("待完成");
        names.add("计划中");
        names.add("未完成");
        mCircle.setData(baifenbi,colors,names);
    }




    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
    }
}
