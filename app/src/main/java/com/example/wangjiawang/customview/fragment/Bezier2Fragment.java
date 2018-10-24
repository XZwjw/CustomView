package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;

import com.example.wangjiawang.customview.R;
import com.example.wangjiawang.customview.widget.Bezier2;

/**
 * Created by wangjiawang on 2018/10/24
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:三阶贝塞尔曲线测试类
 */
public class Bezier2Fragment extends Fragment implements View.OnClickListener {

    private Bezier2 bezier2;
    private RadioButton control1,control2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bezier2_layout,container,false);
        initView(view);
        initClick();
        return view;

    }


    private void initClick() {
        control1.setOnClickListener(this);
        control2.setOnClickListener(this);
    }

    private void initView(View view) {
        bezier2 =  view.findViewById(R.id.bezier2);
        control1 = view.findViewById(R.id.control1);
        control2 = view.findViewById(R.id.control2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.control1:
                bezier2.setMode(Bezier2.MODE_CONTROL_CONTROL_ONE);
                break;
            case R.id.control2:
                bezier2.setMode(Bezier2.MODE_CONTROL_CONTROL_TWO);
                break;
        }
    }
}
