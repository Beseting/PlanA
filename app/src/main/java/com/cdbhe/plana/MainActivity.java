package com.cdbhe.plana;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.cdbhe.plib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {
    @BindView(R.id.main_view)LinearLayout main_view;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        hideTitleBar();
    }
}