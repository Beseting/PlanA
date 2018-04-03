package com.cdbhe.plana;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.http.common.RequestParams;
import com.cdbhe.plib.http.retrofit.ICommonHttpCallback;
import com.cdbhe.plib.http.retrofit.IBaseBiz;
import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.router.PRouter;
import com.cdbhe.plib.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ICommonHttpCallback,IBaseBiz{
    @BindView(R.id.frameLayout)FrameLayout frameLayout;
    TestFragment testFragment;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        hideTitleBar();
//        setStatusBarColor(Color.parseColor("#FE5E4B"));

        RequestParams.getInstance().addParam("token", "18383930457");
        RetrofitClient.getInstance().doPost("api/configure/findEmployee",RequestParams.paramMap, this, this);

        testFragment = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout,testFragment);
        transaction.commit();
    }

    @OnClick({R.id.btn1,R.id.btn2})
    public void click(View view){
        if(view.getId() == R.id.btn1){
            setIsShowStatusBar(true);
        }else {
            setIsShowStatusBar(false);
        }
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toActivity() {

    }

    @Override
    public void onResponse(int requestCode, Object data) {

    }

    @Override
    public void onSuccess(int requestCode, Object data) {

    }

    @Override
    public void onException(int requestCode, Object data) {

    }

    @Override
    public void onError(int requestCode, Throwable e) {

    }
}