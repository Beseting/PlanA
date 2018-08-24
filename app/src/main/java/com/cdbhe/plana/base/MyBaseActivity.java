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
        ButterKnife.bind(this);
        setTitleBarBg(Color.parseColor("#445b53"));
        initView(savedInstanceState);
    }
}
