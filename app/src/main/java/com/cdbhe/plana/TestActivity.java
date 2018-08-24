package com.cdbhe.plana;

import android.os.Bundle;

import com.cdbhe.plana.base.MyBaseActivity;

public class TestActivity extends MyBaseActivity {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("自定义控件实例");
    }
}