package com.cdbhe.plana;

import android.os.Bundle;

import com.cdbhe.plib.base.BaseActivity;

public abstract class MyBaseActivity extends BaseActivity {

    public abstract int getLayoutResId();
    public abstract void initView(Bundle var1);

    @Override
    public int getContentViewResId() {
        return getLayoutResId();
    }

    @Override
    public void init(Bundle savedInstanceState) {
        initView(savedInstanceState);
    }
}
