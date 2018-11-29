package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjiawang.customview.R;


/**
 * Created by wangjiawang on 2018/10/29
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getInflater(),container,false);
    }

    protected int getInflater() {
        return R.layout.normal_layout;
    }

    /**
     * 判断当前是哪个Fragment
     * @return
     */
    public abstract int getType();
}
