package com.cdbhe.plana;

import android.os.Bundle;
import android.view.View;

import com.cdbhe.plib.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/1.
 */

public class TestFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_router_test;
    }

    @Override
    public void initBindInject(Object target,View view) {
        ButterKnife.bind(target,view);
    }

    @Override
    public void init(Bundle savedInstanceState) {

    }

}
