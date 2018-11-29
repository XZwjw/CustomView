package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wangjiawang on 2018/11/23
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:画布变换后坐标转换问题
 */
public class CanvasVonvertTouchTest extends CustomView {
    private static final int OFFSET = 10;   //为了看见坐标系所设置的偏移量
    float down_x = -1;
    float down_y = -1;

    public CanvasVonvertTouchTest(Context context) {
        this(context,null);
    }

    public CanvasVonvertTouchTest(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    down_x = event.getX();
                    down_y = event.getY();
                    invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
                case MotionEvent.ACTION_UP:
                    down_x = down_y = -1;
                    invalidate();
                    break;
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float x = down_x;
        float y = down_y;

        drawTouchCoordinateSpace(canvas);   //绘制触摸坐标系 灰色

        //画布平移
        canvas.translate(mViewWidth,mViewHeight);

    }

    private void drawTouchCoordinateSpace(Canvas canvas) {
        canvas.save();
        canvas.translate(OFFSET,OFFSET);
        Paint paint = new Paint();
        paint.setColor(Color.GRAY);
        Path pathX = new Path();
        pathX.moveTo(OFFSET,mCenterY);
        pathX.lineTo(2000,mCenterY);
        Path pathY = new Path();
        pathY.moveTo(mCenterX,OFFSET);
        pathY.lineTo(mCenterX,2000);
        canvas.drawPath(pathX,paint);
        canvas.drawPath(pathY,paint);
    }


}
