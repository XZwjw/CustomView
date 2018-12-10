package com.example.wangjiawang.customview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wangjiawang on 2018/12/10
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class TaiJi extends View {

    private Paint mBlackPaint;  //黑笔
    private Paint mWritePaint;  //白笔

    private float mWidth;       //宽度
    private float mHeight;      //高度

    private float mRadius;      //半径

    private float mCenterX;     //宽度中心
    private float mCenterY;     //高度中心

    private int mAngle = 0;      //旋转角度

    public TaiJi(Context context) {
        this(context, null);
    }

    public TaiJi(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TaiJi(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mBlackPaint = new Paint();
        mBlackPaint.setAntiAlias(true);
        mBlackPaint.setStyle(Paint.Style.FILL);
        mBlackPaint.setTextSize(8);
        mBlackPaint.setColor(Color.BLACK);

        mWritePaint = new Paint();
        mWritePaint.setAntiAlias(true);
        mWritePaint.setStyle(Paint.Style.FILL);
        mWritePaint.setTextSize(8);
        mWritePaint.setColor(Color.WHITE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY);
        canvas.drawColor(Color.GRAY);
        canvas.rotate(mAngle);

        mRadius = (int) (Math.min(mWidth, mHeight) / 2 - 100);
        RectF rect = new RectF(-mRadius, -mRadius, mRadius, mRadius);

        //绘制两个半圆
        canvas.drawArc(rect, 90, -180, true, mBlackPaint);
        canvas.drawArc(rect, 90, 180, true, mWritePaint);

        //绘制两个小圆
        float radius = mRadius / 2;
        canvas.drawCircle(0, -radius, radius, mBlackPaint);
        canvas.drawCircle(0, radius, radius, mWritePaint);

        //绘制两个鱼眼
        float fishEye = radius / 4;
        canvas.drawCircle(0, -radius, fishEye, mWritePaint);
        canvas.drawCircle(0, radius, fishEye, mBlackPaint);
    }

    //设置角度
    private void setAngle(int angle) {
        mAngle = angle;
    }
}
