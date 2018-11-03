package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wangjiawang.customview.R;
import com.example.wangjiawang.customview.tool.FragmentType;

/**
 * Created by wangjiawang on 2018/10/31
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:matrix分支
 */
public class TestFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.test_layout,container,false);
        return view;
    }

    @Override
    public int getType() {
        return FragmentType.TYPE_TEST;
    }
}
