package com.example.wangjiawang.customview;


import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.wangjiawang.customview.fragment.BaseFragment;
import com.example.wangjiawang.customview.fragment.Bezier2Fragment;
import com.example.wangjiawang.customview.fragment.Bezier3Fragment;
import com.example.wangjiawang.customview.fragment.BezierFragment;
import com.example.wangjiawang.customview.tool.FragmentType;


public class MainActivity extends AppCompatActivity {
    private FragmentManager mManager;
    private BaseFragment mBaseFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);
        mManager = getSupportFragmentManager();
        //方法1
        loadFragment(new Bezier3Fragment());
        Log.d("TAG","我是test 2");
    }

    /**
     * 加载fragment
     */
    private void loadFragment(BaseFragment fragment) {
        if(fragment != null) {
            mManager.beginTransaction().replace(R.id.fragment,fragment).commit();
        }
    }

    /**
     * 根据type加载fragment
     * @param type 类型
     * loadFragmentByType(FragmentType.TYPE3);
     */
    private void loadFragmentByType(int type) {
        switch (type) {
            case FragmentType.TYPE1:
                    mBaseFragment = new BezierFragment();
                break;
            case FragmentType.TYPE2:
                    mBaseFragment = new Bezier2Fragment();
                break;
            case FragmentType.TYPE3:
                    mBaseFragment = new Bezier3Fragment();
                break;
        }
        if(mBaseFragment != null) {
            mManager.beginTransaction().replace(R.id.fragment,mBaseFragment).commit();
        }
    }

}
