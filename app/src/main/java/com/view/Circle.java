package com.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class Circle extends View {

    private String TAG = Circle.class.getName();
    private int startAngle = 0; // 默认起点角度为0
    private int sweepAngle = 0;
    private int roundAngle = 360;
    private RectF mRectF;

    private ArrayList<Integer> mPercentage;
    private ArrayList<Integer> mColors;
    private ArrayList<Paint> mPaints = new ArrayList<>();

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    public Circle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public Circle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /**
     * 用户传递数据
     *
     * @param percentage 百分比数组
     */
    public void setData(ArrayList<Integer> percentage) {
        if (percentage != null && percentage.size() > 0) {
            this.mPercentage = percentage;
        } else {
            Log.e(TAG, "percentage is null or length = 0 ");
        }
    }

    /**
     * 用户传递数据
     *
     * @param percentage 百分比数组
     * @param colors     颜色数组 按照数组的下标对应
     */
    public void setData(ArrayList<Integer> percentage, ArrayList<Integer> colors) {
        Log.e(TAG, "setData: " );
        if (percentage != null && percentage.size() > 0 && colors != null && colors.size() > 0) {
            this.mPercentage = percentage;
            this.mColors = colors;
        } else {
            Log.e(TAG, "percentage or color is null or length = 0 ");
        }
    }

    /**
     * 用户传递数据
     *
     * @param percentage 百分比数组
     * @param colors     颜色数组 按照数组的下标对应
     * @param names      名字
     */
    public void setData(int[] percentage, int[] colors, String[] names) {


    }

    private void init() {
        Log.e(TAG, "init: ");
        int width = getWidth();
        int height = getHeight();
        int length = Math.min(width, height);
        mRectF = new RectF(length * 0.1f, length * 0.1f, length * 0.9f,
                length * 0.9f);
        for (int i = 0; i < this.mPercentage.size(); i++) {
            Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mArcPaint.setColor(this.mColors.get(i));
            mArcPaint.setStyle(Paint.Style.STROKE);
            mArcPaint.setStrokeWidth((width * 0.1f));
            mPaints.add(mArcPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        init();
        drawSth(canvas);
    }

    private void drawSth(Canvas canvas) {
        Log.e(TAG, "drawSth: ");
        for (int i = 0; i < mPaints.size(); i++) {
            sweepAngle = (int) (startAngle + (mPercentage.get(i).doubleValue() / 100 * roundAngle));
            Log.e(TAG, "startAngle: "+startAngle);
            Log.e(TAG, "sweepAngle: "+sweepAngle);
            canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaints.get(i));
            startAngle = sweepAngle;
        }
    }


}
