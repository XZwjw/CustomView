package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Toast;

/**
 * Created by wangjiawang on 2018/11/23
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:绘制不规则形状图的点击处理
 */
public class RegionClickView extends CustomView{
    private Region circleRegion;
    Path circlePath;

    public RegionClickView(Context context) {
        this(context,null);
    }

    public RegionClickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mDefaultPaint.setColor(0xFF4E5268);
        circlePath = new Path();
        circleRegion = new Region();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //在屏幕中添加一个圆
        circlePath.addCircle(w/2,h/2,300,Path.Direction.CW);
        Region globalRegion = new Region(-w,-h,w,h);
        circleRegion.setPath(circlePath,globalRegion);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                int x = (int) event.getX();
                int y = (int) event.getY();

                //点击区域判断
                if(circleRegion.contains(x,y)) {
                    Toast.makeText(this.getContext(),"圆被点击",Toast.LENGTH_SHORT).show();
                }
                break;

        }
        return true;
    }


//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        String TAG = "Wjw";
//        int index = event.getActionIndex();
//        switch (event.getActionMasked()) {
//            case MotionEvent.ACTION_DOWN:
//                Log.e(TAG,"第一个手指按下");
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.e(TAG,"最后一个手指抬起");
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                Log.e(TAG,"第" + (index+1)+"个手指按下");
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.e(TAG,"第" + (index+1)+"个手指抬起");
//                break;
//        }
//        return true;
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        //将全局变量转换为局部变量，方便GC回收canvas
        Path circle = circlePath;
        //绘制圆
        canvas.drawPath(circle,mDefaultPaint);
    }
}
