package com.cdbhe.plana;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.cdbhe.plib.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        hideStatusBar();

        testFragment = new TestFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.frameLayout,testFragment);
        transaction.commit();
    }
}