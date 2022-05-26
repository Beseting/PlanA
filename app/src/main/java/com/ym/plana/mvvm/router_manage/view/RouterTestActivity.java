package com.ym.plana.mvvm.router_manage.view;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plib.router.PRouter;

import butterknife.BindView;
import butterknife.OnClick;

public class RouterTestActivity extends MyBaseActivity {
    @BindView(R.id.nameTv)
    TextView nameTv;
    @BindView(R.id.ageTv)
    TextView ageTv;
    @BindView(R.id.sexTv)
    TextView sexTv;
    @BindView(R.id.btn)
    Button btn;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_router_test;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("路由测试");
        if (TextUtils.isEmpty(PRouter.getString("name"))) {// 无参路由
            nameTv.setText("name：无参数");
            ageTv.setText("age：无参数");
            sexTv.setText("sex：无参数");
        } else {// 有参路由
            nameTv.setText("name：" + PRouter.getString("name"));
            ageTv.setText("age：" + PRouter.getInt("age"));
            sexTv.setText("sex：" + (PRouter.getBoolean("isMale") ? "男" : "女"));
        }
        btn.setVisibility(PRouter.getBoolean("isResult") ? View.VISIBLE : View.GONE);
    }

    @OnClick(R.id.btn)
    public void click(View view) {
        Intent intent = new Intent();
        intent.putExtra("content", "123");
        setResult(RESULT_OK, intent);
        finish();
    }
}