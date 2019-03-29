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

    private final String TAG = Circle.class.getName();
    private final float DEFAULT_TIP_NAME_SIZE = 34f;
    private final float DEFAULT_RADIUS = 0;
    private final float DEFAULT_CIRCLE_SIZE = 10;
    private final boolean DEFAULT_FILL_MODE = true;
    private final int DEFAULT_TEXT_COLOR = Color.BLACK;

    private final float roundAngle = 360f;
    private RectF mRectF;
    private float circleSize;// 统计图宽度
    private float left;
    private float right;
    private float top;
    private float bottom;
    private float radius; // 半径
    private float tipNameSize;  //提示文字的大小
    private Paint linePaint; //提示线条画笔
    private Paint textPaint;
    private boolean fillMode; // true 为填充，false为圆环
    private int textColor;

    private ArrayList<Integer> mPercentage;
    private ArrayList<Integer> mColors;
    private ArrayList<Paint> mPaints = new ArrayList<>();
    private ArrayList<String> names = new ArrayList<>();


    public Circle(Context context) {
        super(context);
    }

    public Circle(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.Circle);
        if (ta != null) {
            circleSize = ta.getDimension(R.styleable.Circle_circleSize, DEFAULT_CIRCLE_SIZE);
            radius = ta.getDimension(R.styleable.Circle_circleRadiusSize, DEFAULT_RADIUS);
            tipNameSize = ta.getDimension(R.styleable.Circle_textSize, DEFAULT_TIP_NAME_SIZE);
            fillMode = ta.getBoolean(R.styleable.Circle_fill, DEFAULT_FILL_MODE);
            textColor = ta.getColor(R.styleable.Circle_textSize,DEFAULT_TEXT_COLOR);
        }
        ta.recycle();
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
        if (isListNull(percentage)) {
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
        if (isListNull(percentage) && isListNull(colors)) {
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
    public void setData(ArrayList<Integer> percentage, ArrayList<Integer> colors, ArrayList<String> names) {
        if (isListNull(percentage) && isListNull(colors) && isListNull(names)) {
            this.mPercentage = percentage;
            this.mColors = colors;
            this.names = names;
        } else {
            Log.e(TAG, "percentage or color is null or length = 0 ");
        }

    }

    private void init() {
        Log.e(TAG, "init: ");
        int width = getWidth();
        int height = getHeight();
        // 如果用户没有输入圆的半径 就默认让其居中
        int length = Math.min(width, height);
        if (radius == 0) {
            top = circleSize + getPaddingBottom();
            left = circleSize + getPaddingLeft() + (width - height) / 2;
            right = left + length - circleSize - getPaddingRight();
            bottom = top + length - circleSize - getPaddingBottom();
            radius = (right - left) / 2;
        } else {
            float diameter = radius * 2;
            top = circleSize + (height - diameter) / 2 + getPaddingBottom();
            left = circleSize + (width - diameter) / 2 + getPaddingBottom();
            bottom = top + diameter;
            right = left + diameter;
        }

        mRectF = new RectF(left, top, right, bottom);
        for (int i = 0; i < this.mPercentage.size(); i++) {
            Paint mArcPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            mArcPaint.setColor(this.mColors.get(i));
            mArcPaint.setStyle(fillMode ? Paint.Style.FILL : Paint.Style.STROKE);
            mArcPaint.setStrokeWidth(circleSize);
            mPaints.add(mArcPaint);
        }

        linePaint = new Paint();
        linePaint.setStyle(Paint.Style.FILL);
        linePaint.setColor(Color.BLACK);
        linePaint.setStrokeWidth(10);

        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(textColor);
        textPaint.setTextSize(tipNameSize);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e(TAG, "onDraw: ");
        init();
        try {
            drawSth(canvas);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void drawSth(Canvas canvas) {
        float startAngle = 0; // 默认起点角度为0
        float sweepAngle;
        Log.e(TAG, "drawSth: ");
        for (int i = 0; i < mPaints.size(); i++) {
            sweepAngle = (mPercentage.get(i).floatValue() / 100f * roundAngle);
            canvas.drawArc(mRectF, startAngle, sweepAngle, fillMode, mPaints.get(i));
            if (fillMode) {
                showTip(startAngle, sweepAngle, canvas, names.get(i));
            } else {
                showLineTip(startAngle, sweepAngle, canvas, names.get(i));
            }
            startAngle += sweepAngle;
        }
    }

    /**
     * 展示园百分比图时候提示
     */
    private void showTip(float startAngle, float sweepAngle,Canvas canvas,String tipName) {
        float tipAngle = startAngle + sweepAngle / 2;
        float x = (float) (radius+radius / 2 * Math.cos(tipAngle * Math.PI / 180)+left);    //计算文字位置坐标
        float y = (float) (radius+ radius / 2 * Math.sin(tipAngle * Math.PI / 180)+top);
        canvas.drawText(tipName, x, y, textPaint);
    }

    /**
     * 展示圆环百分比图的提示
     */
    private void showLineTip(float startAngle, float sweepAngle, Canvas canvas, String tipName) {
        float tipAngle = startAngle + sweepAngle / 2;
        float x = (float) (radius + radius * Math.cos((sweepAngle / 2 + startAngle) * Math.PI / 180)) + left;
        float y = (float) (radius + radius * Math.sin((sweepAngle / 2 + startAngle) * Math.PI / 180)) + top;
        float stopX;
        float stopY;
        float hrStopX;
        if (tipAngle < 90) {
            stopX = x + (radius / 2);
            stopY = y + (radius / 2);
            hrStopX = stopX + radius;
        } else if (tipAngle > 90 && tipAngle < 180) {
            stopX = x - (radius / 2);
            stopY = y + (radius / 2);
            hrStopX = stopX - radius;
        } else if (tipAngle > 180 && tipAngle < 270) {
            stopX = x - (radius / 2);
            stopY = y - (radius / 2);
            hrStopX = stopX - radius;
        } else {
            stopX = x + (radius / 2);
            stopY = y - (radius / 2);
            hrStopX = stopX + radius;
        }
        canvas.drawLine(x, y, stopX, stopY, linePaint);
        canvas.drawLine(stopX, stopY, hrStopX, stopY, linePaint);
        canvas.drawText(tipName, stopX, stopY - 10, textPaint);
    }

    private boolean isListNull(ArrayList list) {
        return list != null && list.size() > 0;
    }


}
