package com.cdbhe.plana;

import android.os.Bundle;
import android.widget.ListView;

import com.cdbhe.plib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity{
    @BindView(R.id.listView)ListView listView;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("新设置的标题");
    }
}