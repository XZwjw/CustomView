package com.example.wangjiawang.customview.widget;


import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import android.graphics.Canvas;
import android.graphics.Matrix;

import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;

import android.util.Log;
import android.view.View;

import com.example.wangjiawang.customview.R;

/**
 * Created by wangjiawang on 2018/10/30
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class PathMeasureView extends View {
    private Paint mPaint;
    private float centerX,centerY;
    private float currentValue = 0; //用于记录当前的位置，取值范围【0，1】映射Path的整个长度
    private float[] pos;            //当前点的实际位置
    private float[] tan;            //当前点的tangent值，用于计算图片所需旋转的角度
    private Bitmap mBitmap;         //箭头图片
    private Matrix mMatrix;         //矩阵，用于对图片进行一些操作
    public PathMeasureView(Context context) {
        super(context);
    }

    public PathMeasureView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.STROKE);

        pos = new float[2];
        tan = new float[2];
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8;       //缩放图片
        mBitmap = BitmapFactory.decodeResource(context.getResources(),R.drawable.arrow,options);
        mMatrix = new Matrix();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;
    }

    @Override
    @SuppressLint("DrawAllocation")
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(centerX,centerY);  //平移坐标系
        Path path = new Path();     //创建path
        path.addCircle(0,0,200,Path.Direction.CW);
        PathMeasure measure = new PathMeasure(path,false);
        currentValue += 0.005;
        if(currentValue >= 1) {
            currentValue = 0;
        }
        //方法1
//        measure.getPosTan(measure.getLength()* currentValue,pos,tan);   //获取坐标与趋势
//
//        mMatrix.reset();
//        float degrees = (float) (Math.atan2(tan[1],tan[0])*180/Math.PI);        //获取角度,Math.atan2:获取角度大小，单位为弧度。
//
//        mMatrix.postRotate(degrees,mBitmap.getWidth()/2,mBitmap.getHeight() /2);    //旋转图片
//        mMatrix.postTranslate(pos[0] - mBitmap.getWidth()/2,pos[1] - mBitmap.getHeight()/2);    //将图片绘制中心与当前坐标重叠
        //方法2
        measure.getMatrix(measure.getLength()* currentValue,mMatrix,PathMeasure.POSITION_MATRIX_FLAG|PathMeasure.TANGENT_MATRIX_FLAG);
        mMatrix.preTranslate(-mBitmap.getWidth()/2,-mBitmap.getHeight()/2);

        canvas.drawPath(path,mPaint);
        canvas.drawBitmap(mBitmap,mMatrix,mPaint);

        invalidate();
    }
}
