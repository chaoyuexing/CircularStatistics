package com.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Circle mCircle;
    private ArrayList<Integer> baifenbi = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainLayout = findViewById(R.id.main_layout);
        mCircle = findViewById(R.id.mCircle);
        baifenbi.add(10);
        baifenbi.add(20);
        baifenbi.add(40);
        baifenbi.add(30);
        colors.add(Color.BLACK);
        colors.add(Color.BLUE);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        mCircle.setData(baifenbi,colors);
        ViewParent viewParent = mainLayout.getParent();
        Log.i("TAG", "the parent of mainLayout is " + viewParent);
    }


    // 圆形统计图      %比，颜色，名字


}
