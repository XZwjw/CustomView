package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by wangjiawang on 2018/11/23
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:共用自定义View,初始化paint
 */
public class CustomView extends View {
    protected Paint mDefaultPaint;
    protected int mCenterX,mCenterY;
    protected float mViewWidth,mViewHeight;

    public CustomView(Context context) {
        this(context,null);
    }



    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDefaultPaint = new Paint();
        mDefaultPaint.setColor(Color.BLACK);
        mDefaultPaint.setStrokeWidth(8);
        mDefaultPaint.setTextSize(20);
        mDefaultPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mViewWidth = w;
        mViewHeight = h;
        mCenterX = w/2;
        mCenterY = h/2;

    }
}
