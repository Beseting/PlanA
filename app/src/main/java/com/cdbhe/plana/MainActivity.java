package com.cdbhe.plana;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.http.retrofit.CommonHttpCallback;
import com.cdbhe.plib.http.retrofit.IBaseBiz;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements CommonHttpCallback,IBaseBiz {
    @BindView(R.id.button)Button button;
    @BindView(R.id.tv)TextView tv;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    public void click(View view){

    }

    @Override
    public void onSuccess(int requestCode, Object data) {
        tv.setText(data.toString());
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toActivity() {

    }
}