package com.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewParent;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private LinearLayout mainLayout;
    private Circle mCircle;
    private ArrayList<Integer> baifenbi = new ArrayList<>();
    private ArrayList<Integer> colors = new ArrayList<>();
    private String TAG = MainActivity.class.getSimpleName();
    private boolean isRun = true;

    private Thread thread;
    private Button start;
    private Button run;
    private Button join;
    private Button wait;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start = findViewById(R.id.start);
        run = findViewById(R.id.run);
        join = findViewById(R.id.join);
        wait = findViewById(R.id.wait);
        thread = new Thread() {
            @Override
            public void run() {
                super.run();
                int i = 0;
                while (isRun){
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.i(TAG, "run: "+i);
                    i++;
                    interrupt();
                }
            }
        };
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                thread.start();
                isRun = true;
            }
        });

        run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isRun = false;
            }
        });

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    thread.join(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        wait.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    synchronized(this) {
                        thread.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

    }


    // 圆形统计图      %比，颜色，名字


    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "onDestroy: " );
        isRun = false;
        thread.run();
        thread.stop();
    }
}
