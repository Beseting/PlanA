package com.cdbhe.plana;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cdbhe.plana.httpdemo.HttpDemoActivity;
import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.http.retrofit.IBaseBiz;
import com.cdbhe.plib.http.retrofit.ICommonHttpCallback;
import com.cdbhe.plib.router.PRouter;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements ICommonHttpCallback,IBaseBiz,BaseActivity.OnTitleBarListener{
//    @BindView(R.id.frameLayout)FrameLayout frameLayout;
    TestFragment testFragment;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
//        hideTitleBar();
        setTitle("新设置的标题");
//        setTitleBarBg(Color.RED);
//        setStatusBarColor(Color.RED);
        showMore(this);
        PRouter.getInstance().navigation(this,HttpDemoActivity.class);
//        setStatusBarColor(Color.parseColor("#FE5E4B"));

//        RequestParams.getInstance().addParam("token", "18383930457");
//        RetrofitClient.getInstance().doPost("api/configure/findEmployee",RequestParams.paramMap, this, this);

//        testFragment = new TestFragment();
//        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
//        transaction.add(R.id.frameLayout,testFragment);
//        transaction.commit();
//        PRouter.getInstance().navigation(this,TestActivity.class,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @OnClick({R.id.btn1})
    public void click(View view){
        if(view.getId() == R.id.btn1){
//            setIsShowStatusBar(true);
//            showDialog();
            PRouter.getInstance().withString("test","测试参数").navigation(this,TestActivity.class);
        }
//        else if(view.getId() == R.id.btn2){
//            setIsShowStatusBar(false);
//        }else{
//
//        }
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

    @Override
    public void clickMore(View view) {
    }
}