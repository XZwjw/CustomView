package com.example.wangjiawang.customview.animation;

import android.content.Context;
import android.graphics.Camera;
import android.graphics.Matrix;
import android.view.animation.Animation;
import android.view.animation.Transformation;

/**
 * Created by wangjiawang on 2018/11/3
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class Rotate3dAnimation extends Animation {
    private final float mFromDegrees;
    private final float mToDegrees;
    private final float mCenterX;
    private final float mCenterY;
    private final float mDepthZ;
    private final boolean mReverse;
    private Camera mCamera;


    public Rotate3dAnimation(Context context,float mFromDegrees, float mToDegrees, float mCenterX, float mCenterY, float mDepthZ, boolean mReverse) {
        this.mFromDegrees = mFromDegrees;
        this.mToDegrees = mToDegrees;
        this.mCenterX = mCenterX;
        this.mCenterY = mCenterY;
        this.mDepthZ = mDepthZ;
        this.mReverse = mReverse;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {
        super.initialize(width, height, parentWidth, parentHeight);
        mCamera = new Camera();
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        final float fromDegrees = mFromDegrees;
        float degrees = fromDegrees +((mToDegrees - fromDegrees)*interpolatedTime);
        final float centerX = mCenterX;
        final float centerY = mCenterY;
        final Camera camera = mCamera;
        final Matrix matrix = t.getMatrix();
        camera.save();

        //调节深度
        if(mReverse) {
            camera.translate(0.0f,0.0f,mDepthZ * interpolatedTime);
        }else {
            camera.translate(0.0f,0.0f,mDepthZ *(1.0f - interpolatedTime));
        }

        //绕y轴绕转
        camera.rotateY(degrees);

        camera.getMatrix(matrix);
        camera.restore();

        //调解中心点
        matrix.preTranslate(-centerX,-centerY);
        matrix.postTranslate(centerX,mCenterY);
    }
}
