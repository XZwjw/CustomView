package com.example.wangjiawang.customview.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import com.example.wangjiawang.customview.R;

/**
 * Created by wangjiawang on 2018/12/5
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:可以拖动的 Ball
 */
public class FailingBall extends View {

    private int mWidth;     //宽度
    private int mHeight;    //高度

    private float mStartX;  //小方块开始位置X
    private float mStartY;  //小方块开始位置Y
    private float mEdgeLength = 200;        //边长
    private RectF mRect = new RectF(mStartX, mStartY, mStartX + mEdgeLength, mStartY + mEdgeLength);

    private float mFixedX = 0;  //修正距离X
    private float mFixedY = 0;  //修正距离Y

    private Paint mPaint;

    private GestureDetector mGestureDetector;
    private boolean mCanFail = false;

    private float mSpeedX;     //像素/s
    private float mSpeedY;

    private Boolean mXFixed = false;
    private Boolean mYFixed = false;

    private Bitmap mBitmap;

    public FailingBall(Context context) {
        this(context, null);
    }

    public FailingBall(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FailingBall(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mGestureDetector = new GestureDetector(context, mSimpleOnGestureListener);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setAntiAlias(true);

        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ball);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
        mStartX = (w - mEdgeLength) / 2;
        mStartY = (h - mEdgeLength) / 2;
        refreshRectByCurrentPoint();
    }

    @SuppressLint("DrawAllocation")
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawOval(mRect, mPaint);
        canvas.drawBitmap(mBitmap, new Rect(0, 0, mBitmap.getWidth(), mBitmap.getHeight()), mRect, mPaint);
    }

    //每 100 ms 更新一次
    private Handler mHandler = new Handler();
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            //刷新内容
            mStartX += mSpeedX / 30;
            mStartY += mSpeedY / 30;

            mSpeedX *= 0.97f;
            mSpeedY *= 0.97f;

            if (Math.abs(mSpeedX) < 10) {
                mSpeedX = 0;
            }
            if (Math.abs(mSpeedY) < 10) {
                mSpeedY = 0;
            }

            if (refreshRectByCurrentPoint()) {
                //转向
                if (mXFixed) {
                    mSpeedX = -mSpeedX;
                }
                if (mYFixed) {
                    mSpeedY = -mSpeedY;
                }
            }
            invalidate();
            if (mSpeedX == 0 && mSpeedY == 0) {
                mHandler.removeCallbacks(this);
                return;
            }
            mHandler.postDelayed(this, 33);
        }
    };

    private GestureDetector.SimpleOnGestureListener mSimpleOnGestureListener = new
            GestureDetector.SimpleOnGestureListener() {
                @Override
                public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                    Log.e("Failing", velocityX + " : " + velocityY);
                    if (!mCanFail) return false;
                    mSpeedX = velocityX;
                    mSpeedY = velocityY;
                    mHandler.removeCallbacks(mRunnable);
                    mHandler.postDelayed(mRunnable, 0);
                    return super.onFling(e1, e2, velocityX, velocityY);
                }
            };

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        mGestureDetector.onTouchEvent(event);
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
                if (contains(event.getX(), event.getY())) {
                    mCanFail = true;
                    mFixedX = event.getX() - mStartX;
                    mFixedY = event.getY() - mStartY;
                    mSpeedX = 0;
                    mSpeedY = 0;
                } else {
                    mCanFail = false;
                }
                break;
            case MotionEvent.ACTION_UP:
                if (!mCanFail) {
                    break;
                }
                mStartX = event.getX() - mFixedX;
                mStartY = event.getY() - mFixedY;
                if (refreshRectByCurrentPoint()) {
                    mFixedX = event.getX() - mStartX;
                    mFixedY = event.getY() - mStartY;
                }
                invalidate();
                break;
        }
        return true;
    }

    /**
     * 判断点是否在圆区域内
     */
    private boolean contains(float x, float y) {
        float radius = mEdgeLength / 2;
        float centerX = mStartX + radius;
        float centerY = mStartY + radius;
        return Math.sqrt(Math.pow(x - centerX, 2) + Math.pow(y - centerY, 2)) <= radius;
    }

    /**
     * 刷新方块位置
     *
     * @return true 表示修正过位置， false 表示没有修正过位置
     */
    private boolean refreshRectByCurrentPoint() {
        Boolean fixed = false;
        mXFixed = false;
        mYFixed = false;

        //修正坐标
        if (mStartX < 0) {
            mStartX = 0;
            fixed = true;
            mXFixed = true;
        }
        if (mStartY < 0) {
            mStartY = 0;
            fixed = true;
            mYFixed = true;
        }
        if (mStartX + mEdgeLength > mWidth) {
            mStartX = mWidth - mEdgeLength;
            fixed = true;
            mXFixed = true;
        }
        if (mStartY + mEdgeLength > mHeight) {
            mStartY = mHeight - mEdgeLength;
            fixed = true;
            mYFixed = true;
        }
        mRect.left = mStartX;
        mRect.top = mStartY;
        mRect.right = mStartX + mEdgeLength;
        mRect.bottom = mStartY + mEdgeLength;
        return fixed;
    }


}
