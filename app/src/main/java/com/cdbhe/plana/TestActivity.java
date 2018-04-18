package com.cdbhe.plana;

import android.os.Bundle;

import com.cdbhe.plib.base.BaseActivity;

public class TestActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTitle("haha");
    }
}
