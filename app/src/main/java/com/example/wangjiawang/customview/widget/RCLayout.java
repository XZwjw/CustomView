package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.wangjiawang.customview.R;

/**
 * Created by wangjiawang on 2018/12/10
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 *  Simple circular layout implementation, because clipPath does not support anti-aliasing so in some
 * low resolution devices on the edge looks rough, so can not carry out anti-aliasing processing,
 * it is recommended to refer to the open source library rclayout
 */
public class RCLayout extends RelativeLayout {

    //定义圆角信息和Path
    private float[] radii = new float[8];       //top-left,top-right,bottom-left,bottom-right
    private Path mClipPath;         //裁剪路径
    private RectF mRectF;

    public RCLayout(Context context) {
        this(context, null);
    }

    public RCLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RCLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //通过自定义属性获取圆角信息
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.RCLayout);
        int roundCorner = array.getDimensionPixelOffset(R.styleable.RCLayout_round_corner, 0);
        int roundCornerTopLeft = array.getDimensionPixelOffset(R.styleable.RCLayout_round_corner_top_let, roundCorner);
        int roundCornerTopRight = array.getDimensionPixelOffset(R.styleable.RCLayout_round_corner_top_right, roundCorner);
        int roundCornerBottomLeft = array.getDimensionPixelOffset(R.styleable.RCLayout_round_corner_bottom_let, roundCorner);
        int roundCornerBottomRight = array.getDimensionPixelOffset(R.styleable.RCLayout_round_corner_bottom_right, roundCorner);

        radii[0] = roundCornerTopLeft;
        radii[1] = roundCornerTopLeft;

        radii[2] = roundCornerTopRight;
        radii[3] = roundCornerTopRight;

        radii[4] = roundCornerBottomLeft;
        radii[5] = roundCornerBottomLeft;

        radii[6] = roundCornerBottomRight;
        radii[7] = roundCornerBottomRight;

        mRectF = new RectF();
        mClipPath  = new Path();
        array.recycle();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mRectF.left = getPaddingLeft();
        mRectF.top = getPaddingTop();
        mRectF.right = w - getPaddingRight();
        mRectF.bottom = h - getPaddingBottom();
        mClipPath.addRoundRect(mRectF, radii, Path.Direction.CW);

    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        canvas.clipPath(mClipPath);
        return super.drawChild(canvas, child, drawingTime);
    }
}
