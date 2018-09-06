package com.cdbhe.plana.mvvm.http.callback;

import android.content.Context;

import com.cdbhe.plana.mvvm.home.view.HomeActivity;
import com.cdbhe.plib.http.model.Data;
import com.cdbhe.plib.http.retrofit.ICommonHttpCallback;
import com.cdbhe.plib.router.PRouter;
import com.cdbhe.plib.utils.ActivityStack;
import com.cdbhe.plib.utils.ToastUtils;

public class CommonRequestCallback implements ICommonHttpCallback {
    private Context context;

    public CommonRequestCallback(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(int i, Object o) { }

    @Override
    public void onSuccess(int i, Object o) { }

    @Override
    public void onException(int i, Object o) {
        Data data = (Data)o;
        ToastUtils.showShort(context,data.getMessage());
        if(data.getStatus() == -1){//身份过期
            ActivityStack.getInstance().clearAllActivity();
            PRouter.getInstance().navigation(context, HomeActivity.class);
        }
    }

    @Override
    public void onError(int i, Throwable throwable) { }
}
