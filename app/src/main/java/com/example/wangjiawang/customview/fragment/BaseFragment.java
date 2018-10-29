package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;


/**
 * Created by wangjiawang on 2018/10/29
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public abstract class BaseFragment extends Fragment {

    /**
     * 判断当前是哪个Fragment
     * @return
     */
    public abstract int getType();
}
