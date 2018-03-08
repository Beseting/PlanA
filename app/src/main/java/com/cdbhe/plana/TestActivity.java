package com.cdbhe.plana;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.router.PRouter;
import com.cdbhe.plib.utils.ActivityStack;

public class TestActivity extends BaseActivity {

    @Override
    public int getContentViewResId() {
        return R.layout.activity_test;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ActivityStack.getInstance().popActivity("MainActivity");
            }
        });
    }
}
