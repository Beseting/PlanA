package com.cdbhe.plana;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;

import com.cdbhe.plib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {
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
}