package com.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;


public class Circle extends View {

    private String TAG = Circle.class.getName();
    private float startAngle = 0; // 默认起点角度为0
    private float sweepAngle = 0;
    private int roundAngle = 360;
    private RectF mRectF;
    private float circleSize;
    private float left;
    private float right;
    private float top;
    private float bottom;
    private float radius; // 半径



    private float strokeWidth = 10f;


    private ArrayList<Integer> mPercentage;
    private ArrayList<Integer> mColors;
    private ArrayList<Paint> mPaints = new ArrayList<>();

    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Circle);
        if (ta != null) {
            circleSize = ta.getDimension(R.styleable.Circle_circle_size,10);
        }
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
//        DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
        int width = getWidth();
        int height = getHeight();
        int length = Math.min(width, height);
        left = circleSize + getPaddingLeft();
        top = circleSize + getPaddingBottom();
        right = length - getPaddingRight() - circleSize;
        bottom = length - getPaddingBottom() - circleSize;
//        Log.e(TAG, "length: "+ length);
        Log.e(TAG, "left: "+ left);
        Log.e(TAG, "top: "+ top);
        Log.e(TAG, "right: "+ right);
        Log.e(TAG, "bottom: "+ bottom);
        radius = (right - left) / 2;
        mRectF = new RectF(left  , top , right, bottom);
        for (int i = 0; i < this.mPercentage.size(); i++) {
            Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mArcPaint.setColor(this.mColors.get(i));
            mArcPaint.setStyle(Paint.Style.STROKE);
            mArcPaint.setStrokeWidth(circleSize);
            mPaints.add(mArcPaint);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        init();
        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        canvas.drawCircle(radius + circleSize,radius + circleSize,6,paint);
        try{
            drawSth(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawSth(Canvas canvas) {
        Log.e(TAG, "drawSth: ");
        for (int i = 0; i < mPaints.size(); i++) {
            sweepAngle = (mPercentage.get(i).floatValue() / 100 * roundAngle);
            Log.e(TAG, "startAngle: "+startAngle);
            Log.e(TAG, "sweepAngle: "+sweepAngle);
            float x1 = (float) (radius + circleSize + radius * Math.cos((sweepAngle/2 + startAngle) * Math.PI/180));
            float y1 = (float) (radius + circleSize + radius * Math.sin((sweepAngle/2 + startAngle) * Math.PI/180));
//            Log.e(TAG, "drawSth  i: "+ i+",y"+ y);
//            Log.e(TAG, "drawSth  i: "+ i+",x"+ x);
            Paint paint = new Paint();
            paint.setColor(Color.GREEN);
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(2);
            canvas.drawArc(mRectF, startAngle, sweepAngle, false, mPaints.get(i));
            canvas.drawCircle(x1 ,y1,6,paint);
//            canvas.drawLine(x1,y1,x1+50,y1+50,paint);
//            canvas.drawLine(x1+50,y1+50,x1+250,y1+50,paint);
            startAngle += sweepAngle;
        }
    }


}
