package com.cdbhe.plana;

import android.content.Context;

import com.cdbhe.plib.http.retrofit.ResCallback;

public class RequestCallback implements ResCallback {
    private Context context;

    public RequestCallback(Context context) {
        this.context = context;
    }


    @Override
    public void onResponse(Object o) {

    }

    @Override
    public void onError(Throwable e) {

    }
}
