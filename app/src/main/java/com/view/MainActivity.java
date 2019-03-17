package com.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewParent;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Circle mCircle;
    private ArrayList<Integer> baifenbi = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DisplayMetrics metrics = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        // 屏幕的分辨率
        int width = metrics.widthPixels;
        int height = metrics.heightPixels;
        Log.e(TAG, "width: "+ width);
        Log.e(TAG, "height: "+ height);

        mainLayout = findViewById(R.id.main_layout);
        mCircle = findViewById(R.id.mCircle);
        baifenbi.add(40);
        baifenbi.add(30);
        baifenbi.add(20);
        baifenbi.add(10);
        colors.add(Color.YELLOW);
        colors.add(Color.RED);
        colors.add(Color.BLUE);
        colors.add(Color.BLACK);
        mCircle.setData(baifenbi,colors);
        ViewParent viewParent = mainLayout.getParent();
        Log.i("TAG", "the parent of mainLayout is " + viewParent);
    }


    // 圆形统计图      %比，颜色，名字


}
