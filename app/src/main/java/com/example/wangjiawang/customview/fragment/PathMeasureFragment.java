package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjiawang.customview.tool.FragmentType;

import com.example.wangjiawang.customview.R;

/**
 * Created by wangjiawang on 2018/10/30
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class PathMeasureFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.path_measure_layout,container,false);
        return view;
    }

    @Override
    public int getType() {
        return FragmentType.TYPE4;
    }
}
