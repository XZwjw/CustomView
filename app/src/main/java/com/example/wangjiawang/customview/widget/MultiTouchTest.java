package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangjiawang on 2018/11/29
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:绘制出第二个手指的位置
 */
public class MultiTouchTest extends CustomView{
    String TAG = "Wjw";

    //含有第二个手指
    private boolean hasSecondPoint = false;

    //记录第二个手指位置
    PointF mPointF = new PointF(0,0);
    public MultiTouchTest(Context context) {
        this(context,null);
    }

    public MultiTouchTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDefaultPaint.setAntiAlias(true);
        mDefaultPaint.setTextAlign(Paint.Align.CENTER);
        mDefaultPaint.setTextSize(30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int index = event.getActionIndex();
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_POINTER_DOWN:
                //判断是否是第二个手指按下
                if(event.getPointerId(index) == 1) {
                    hasSecondPoint = true;
                    mPointF.set(event.getX(),event.getY());
                }
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //判断抬起的手指是否是第二个
                if(event.getPointerId(index) == 1) {
                    hasSecondPoint = false;
                    mPointF.set(0,0);
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if(hasSecondPoint) {
                    //通过pointerId来获取pointerIndex
                    int pointerIndex = event.findPointerIndex(index);
                    mPointF.set(event.getX(pointerIndex),event.getY(pointerIndex));
                }

        }
        invalidate();   //刷新
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(mViewWidth/2,mViewHeight/2);
        canvas.drawText("追踪第二个按下手指的类型",0,0,mDefaultPaint);
        canvas.restore();

        //如果屏幕上有第二个手指绘制出其位置
        if(hasSecondPoint) {
            //绘制第二个手指
            canvas.drawCircle(mPointF.x,mPointF.y,50,mDefaultPaint);
        }


    }
}
