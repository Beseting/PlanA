package com.cdbhe.plana;

import android.content.Context;

import com.cdbhe.plib.http.retrofit.ICommonHttpCallback;
import com.cdbhe.plib.router.PRouter;

public class RequestCallback implements ICommonHttpCallback {
    private Context context;

    public RequestCallback(Context context) {
        this.context = context;
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
