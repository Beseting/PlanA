package com.cdbhe.plana;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.cdbhe.plib.base.BaseFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/2/1.
 */

public class TestFragment extends BaseFragment {
    @BindView(R.id.btn)Button btn;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_test;
    }

    @Override
    public void initBindInject(Object target,View view) {
        ButterKnife.bind(target,view);
    }

    @Override
    public void init(Bundle savedInstanceState) {
        btn.setText("1323213");
    }

}
