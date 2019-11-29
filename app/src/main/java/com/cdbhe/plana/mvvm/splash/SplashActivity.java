package com.cdbhe.plana.mvvm.splash;

import android.os.Bundle;
import android.os.Handler;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.home.view.HomeActivity;
import com.cdbhe.plib.router.PRouter;

public class SplashActivity extends MyBaseActivity {
    private boolean isFinishThisActivity = true;//跳转后关闭此Activity
    @Override
    public int getLayoutResId() {
        return R.layout.activity_splash;
    }

    @Override
    public void initView(Bundle var1) {
        setIsFullScreen(true);//全屏
        hideTitleBar();//隐藏标题栏
        // 2s后跳转到HomeActivity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                PRouter.getInstance().navigation(SplashActivity.this, HomeActivity.class,isFinishThisActivity);
            }
        },2000);
    }
}
