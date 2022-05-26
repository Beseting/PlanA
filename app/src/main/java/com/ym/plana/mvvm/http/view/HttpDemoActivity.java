package com.ym.plana.mvvm.http.view;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plana.mvvm.http.biz.IHttpDemoBiz;
import com.ym.plana.mvvm.http.vm.HttpDemoVM;
import com.ym.plib.utils.ToastUtils;

import butterknife.BindView;
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

    @OnClick({R.id.requestBtn,R.id.cancelBtn})
    public void click(View view){
        if(view.getId()==R.id.requestBtn) {
            if (!TextUtils.isEmpty(getSearchContent())) {
                vm = new HttpDemoVM(this);
                vm.doRequest();
            } else {
                ToastUtils.showShort(this, "请输入搜索内容");
            }
        }else{
            vm.cancel();
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
