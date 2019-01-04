package com.cdbhe.plana.base;

import android.graphics.Color;
import android.os.Bundle;

import com.cdbhe.plib.base.BaseActivity;

import butterknife.ButterKnife;

public abstract class MyBaseActivity extends BaseActivity {

    public abstract int getLayoutResId();
    public abstract void initView(Bundle var1);

    @Override
    public int getContentViewResId() {
        return getLayoutResId();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        // ButterKnife注入
        ButterKnife.bind(this);
        // 设置标题栏背景颜色
        setTitleBarBg(Color.parseColor("#445b53"));
        // 初始化视图
        initView(savedInstanceState);
    }
}
