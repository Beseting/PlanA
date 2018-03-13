package com.cdbhe.plana;

import android.Manifest;
import android.os.Bundle;

import com.cdbhe.plana.databinding.ActivityTestBinding;
import com.cdbhe.plib.base.BaseFragment;
import com.cdbhe.plib.utils.DateUtils;

/**
 * Created by Administrator on 2018/2/1.
 */

public class TestFragment extends BaseFragment {
    @Override
    public int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ActivityTestBinding activityTestBinding = (ActivityTestBinding) getViewDataBinding();
        activityTestBinding.setTest(new TestModel("9527"));
        String a = DateUtils.getDateStr();

        requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},0);


    }

}
