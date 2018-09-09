package com.cdbhe.plana.mvvm.http.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.http.biz.IHttpDemoBiz;
import com.cdbhe.plana.mvvm.http.vm.HttpDemoVM;
import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HttpDemoActivity extends MyBaseActivity implements IHttpDemoBiz {
    @BindView(R.id.contentEt)
    EditText contentEt;
    @BindView(R.id.responseTv)
    TextView responseTv;
    private HttpDemoVM vm;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_http_demo;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("网络请求");
    }

    @OnClick(R.id.requestBtn)
    public void click(View view){
        if(!TextUtils.isEmpty(getSearchContent())){
            vm = new HttpDemoVM(this);
            vm.doRequest();
        }else{
            ToastUtils.showShort(this,"请输入搜索内容");
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toActivity(Object... objects) {
        //刷新UI数据
        responseTv.setText(objects[0].toString());
    }

    @Override
    public String getSearchContent() {
        return contentEt.getText().toString();
    }
}
