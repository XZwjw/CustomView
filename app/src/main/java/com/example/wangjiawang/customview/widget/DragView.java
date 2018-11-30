package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.example.wangjiawang.customview.R;

/**
 * Created by wangjiawang on 2018/11/29
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:可拖拽图片（含多点触控处理）
 */
public class DragView extends CustomView {
    String TAG = "Wjw";

    Bitmap mBitmap;     //图片
    RectF mBitmapRectF; //图片区域
    Matrix mBitmapMatrix;   //控制图片的Matrix

    boolean canDrag = false;
    PointF lastPoint = new PointF(0,0);

    public DragView(Context context) {
        this(context,null);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);

        //调整图片大小
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.girl,options);
        mBitmapRectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
        mBitmapMatrix = new Matrix();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                //判断按下的区域是否在图片范围内
                if(event.getPointerId(index) == 0 && mBitmapRectF.contains(event.getX(),event.getY())) {
                    canDrag = true;
                    lastPoint.set(event.getX(),event.getY());
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                //判断是否是第一个手指
                if(event.getPointerId(index) == 0) {
                    canDrag = false;
                }
            case MotionEvent.ACTION_MOVE:
                //如果存在第一个手指，且这个手指在图片区域内
                if(canDrag) {
                    int pointerIndex = event.findPointerIndex(0);
                    //移动图片
                    mBitmapMatrix.preTranslate(event.getX(pointerIndex) - lastPoint.x,event.getY(pointerIndex) - lastPoint.y);
                    //更新上一次点位置
                    lastPoint.set(event.getX(),event.getY());

                    //更新图片区域
                    mBitmapRectF = new RectF(0,0,mBitmap.getWidth(),mBitmap.getHeight());
                    mBitmapMatrix.mapRect(mBitmapRectF);

                    invalidate();
                }
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,mBitmapMatrix,mDefaultPaint);
    }
}
