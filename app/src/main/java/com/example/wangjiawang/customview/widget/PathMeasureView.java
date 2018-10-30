package com.example.wangjiawang.customview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wangjiawang on 2018/10/30
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class PathMeasureView extends View {
    private Paint mPaint;
    private float mCenterX;
    private float mCenterY;

    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setTextSize(60);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w/2;
        mCenterY = h/2;
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制辅助线
        drawAuxiliaryLine(canvas);

        canvas.translate(mCenterX,mCenterY);

        Path path = new Path();
        path.addRect(-100,-100,100,100,Path.Direction.CW);
        canvas.drawPath(path,mPaint);
        path.addRect(-200,-200,200,200,Path.Direction.CW);

        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(4);
        canvas.drawPath(path,mPaint);

        PathMeasure measure = new PathMeasure(path,false);
        float length1 = measure.getLength();
        measure.nextContour();
        float length2 = measure.getLength();

        Log.d("TAG","第一条路径长度为："+length1);
        Log.d("TAG","第二条路径长度为："+length2);


    }

    private void drawAuxiliaryLine(Canvas canvas) {

        canvas.save();

        canvas.translate(mCenterX,mCenterY);
        mPaint.setColor(Color.RED);
        canvas.drawLine(-2000,0,2000,0,mPaint);
        canvas.drawLine(0,-2000,0,2000,mPaint);

        canvas.restore();
    }
}
