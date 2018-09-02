package com.cdbhe.plana.httpdemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cdbhe.plana.R;
import com.cdbhe.plib.base.BaseActivity;

public class HttpDemoActivity extends BaseActivity implements IHttpDemoBiz{
    private Button requestBtn;
    private TextView nameTv;
    private HttpDemoPresenter presenter;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_http_demo;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        setTitle("网络请求实例");
        requestBtn = findViewById(R.id.requestBtn);
        nameTv = findViewById(R.id.nameTv);
        presenter = new HttpDemoPresenter(this);
        requestBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.doRequest1();
                presenter.doRequest2();
                presenter.doRequest3();
                presenter.doRequest4();
                presenter.doRequest5();
                presenter.doRequest6();
                presenter.doRequest7();
                presenter.doRequest();
            }
        });
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toActivity() { }

    @Override
    public void refreshUIData(String name) {
        //刷新UI视图
        nameTv.setText("请求后："+name);
    }
}
