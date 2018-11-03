package com.example.wangjiawang.customview.widget;

import android.content.Context;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.Arrays;

/**
 * Created by wangjiawang on 2018/11/1
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class TestCustomView extends View {
    private static final String TAG = "TestCustomView";
    public TestCustomView(Context context) {
        this(context,null);
    }

    public TestCustomView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public TestCustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        allTest();
    }

    private void allTest() {
//        mapPointsTest();
//        mapPointsTest2();
//        mapPointsTest3();
        mapRadiusTest();
    }


    /**
     * 计算点基于Matrix变换后的位置（一个参数）
     */
    private void mapPointsTest() {
        float[] pts = new float[]{0,0,80,100,400,300};

        //构造一个Matrix,X坐标错放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,1f);

        //输出pts计算之前数据
        Log.d(TAG,"before: "+Arrays.toString(pts));

        matrix.mapPoints(pts);
        Log.d(TAG,"after:"+Arrays.toString(pts));
    }

    /**
     * 计算点基于Matrix变换后的位置（两个参数）
     */
    private void mapPointsTest2() {
        float[] src = new float[]{0,0,80,100,400,300};
        float[] dst = new float[6];

        //构造一个matrix,x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,1f);

        //输出计算之前的数据
        Log.i(TAG,"before: src="+Arrays.toString(src));
        matrix.mapPoints(dst,src);

        //输出计算后的数据
        Log.d(TAG,"after: src="+Arrays.toString(src));
        Log.d(TAG,"after: dst="+Arrays.toString(dst));
    }

    /**
     * 计算点基于Matrix变换后的位置（五个参数）
     */
    private void mapPointsTest3() {
        float[] src = new float[]{0,0,80,100,400,300};
        float[] dst = new float[6];

        //构造一个Matrix,x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,1f);

        //输出计算之前数据
        Log.i(TAG,"before: src="+Arrays.toString(src));
        Log.i(TAG,"before: dst="+Arrays.toString(dst));

        matrix.mapPoints(dst,0,src,2,2);

        Log.i(TAG,"after: src="+Arrays.toString(src));
        Log.i(TAG,"after: dst="+Arrays.toString(dst));
    }

    /**
     * 测量圆基于Matrix变换后半径的大小，因为圆可能会因为画布变换成椭圆,所以此处测量的是平均半径。
     */
    private void mapRadiusTest(){
        float radius = 100;
        float result = 0;

        //构造一个Matrix,x坐标缩放0.5
        Matrix matrix = new Matrix();
        matrix.setScale(0.5f,1f);
        Log.i(TAG,"before mapRadius: "+radius);
        result = matrix.mapRadius(radius);
        Log.i(TAG,"after mapRadius: "+result);
    }





}
