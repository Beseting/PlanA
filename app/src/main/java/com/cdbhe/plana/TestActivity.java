package com.cdbhe.plana;

import android.os.Bundle;

import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.router.PRouter;
import com.cdbhe.plib.utils.ToastUtils;

public class TestActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTitle("test标题");
        ToastUtils.showShort(this, PRouter.getString("test"));
    }
}
