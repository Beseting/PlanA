package com.ym.plana.mvvm.router_manage.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plib.router.PRouter;
import com.ym.plib.utils.ToastUtils;

import butterknife.OnClick;

public class RouterManageActivity extends MyBaseActivity {

    private final int REQUEST_CODE = 1000;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_router_manage;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("PRouter路由管理");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            ToastUtils.showShort(this,"回调内容："+data.getStringExtra("content"));
        }
    }

    @OnClick({R.id.routerWithoutParamBtn,R.id.routerWithParamBtn,R.id.routerFinishActivityBtn,R.id.routerResultBtn})
    public void click(View view){
        switch ((view.getId())){
            case R.id.routerWithoutParamBtn:// 界面路由（不带参）
                PRouter.getInstance().navigation(this,RouterTestActivity.class);
                break;
            case R.id.routerWithParamBtn:// 界面路由（链式带参）
                PRouter.getInstance().withInt("age",22).withString("name","kevin").withBoolean("isMale",true).navigation(this,RouterTestActivity.class);
                break;
            case R.id.routerFinishActivityBtn:// 界面路由（关闭当前界面）
//                PRouter.getInstance().navigation(this,RouterTestActivity.class,true);
                PRouter.getInstance().withInt("age",22).withString("name","kevin").withBoolean("isMale",true).navigation(this,RouterTestActivity.class,true);
                break;
            case R.id.routerResultBtn:// 界面路由（参数回调）
                PRouter.getInstance().withInt("age",22).withString("name","kevin").withBoolean("isMale",true).withBoolean("isResult",true).navigation(this,RouterTestActivity.class,REQUEST_CODE);
                break;
        }
    }
}
