package com.example.wangjiawang.customview.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import com.example.wangjiawang.customview.MainActivity;
import com.example.wangjiawang.customview.R;
import com.example.wangjiawang.customview.animation.Rotate3dAnimation;

/**
 * Created by wangjiawang on 2018/11/3
 * <p>
 * email:15829348578@163.com
 * <p>
 * description:
 */
public class CameraFragment extends BaseFragment {
    private ImageView imageView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.normal_layout,container,false);
        initView(view);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //计算中心点（这里是使用View的中心作为旋转对面）
                final float centerX = v.getWidth() / 2.0f;
                final float centerY = v.getHeight() / 2.0f;

                //括号内参数分别为（上下文，开始角度，结束角度，x住中心点，y轴中心点，深度，是否扭曲）
                final Rotate3dAnimation rotation = new Rotate3dAnimation(CameraFragment.this.getActivity(),0,180,centerX,centerY,0f,true);

                rotation.setDuration(3000);     //设置动画时长
                rotation.setFillAfter(true);    //保持旋转后效果
                rotation.setInterpolator(new LinearInterpolator());     //设置插值器
                v.startAnimation(rotation);
            }
        });
        return view;
    }

    private void initView(View view) {
        imageView = view.findViewById(R.id.imageView);
    }

    @Override
    public int getType() {
        return 0;
    }
}
