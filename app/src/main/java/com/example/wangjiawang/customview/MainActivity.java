package com.example.wangjiawang.customview;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wangjiawang.customview.fragment.Bezier2Fragment;
import com.example.wangjiawang.customview.fragment.BezierFragment;


public class MainActivity extends AppCompatActivity {
    private FragmentManager mManager;
    private BezierFragment mBezierFragment = new BezierFragment();      //二阶贝塞尔曲线
    private Bezier2Fragment mBezier2Fragment = new Bezier2Fragment();    //三阶贝塞尔曲线
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        mManager = getSupportFragmentManager();
        goToBezier();  //加载二阶贝塞尔曲线fragment

    }

    //加载二阶贝塞尔曲线fragment
    private void goToBezier() {
        if(mBezierFragment == null) {
            mBezierFragment = new BezierFragment();
        }
        fragmentChange(mBezierFragment);
    }

    //加载三阶贝塞尔曲线fragment
    private void goToBezier2() {
        if(mBezier2Fragment == null) {
            mBezier2Fragment = new Bezier2Fragment();
        }
        fragmentChange(mBezier2Fragment);
    }

    //替换fragment
    private void fragmentChange(Fragment fragment) {
        mManager.beginTransaction().replace(R.id.fragment,fragment).commit();
    }

}
