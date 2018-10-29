package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjiawang.customview.R;
import com.example.wangjiawang.customview.tool.FragmentType;

/**
 * Created by wangjiawang on 2018/10/29
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class Bezier3Fragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bezier3_layout,container,false);
        return view;
    }

    @Override
    public int getType() {
        return FragmentType.TYPE3;
    }
}
