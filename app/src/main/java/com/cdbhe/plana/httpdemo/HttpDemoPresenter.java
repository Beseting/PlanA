package com.cdbhe.plana.httpdemo;

import android.content.Context;
import android.os.Environment;

import com.cdbhe.plib.http.common.RequestParams;
import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.utils.LogUtils;

import java.io.File;

public class HttpDemoPresenter extends CommonRequestCallback{
    private IHttpDemoBiz iHttpDemoBiz;
    public HttpDemoPresenter(IHttpDemoBiz iHttpDemoBiz) {
        super(iHttpDemoBiz.getActivity());
        this.iHttpDemoBiz = iHttpDemoBiz;
    }

    public void doRequest(){
        File file = new File(Environment.getExternalStorageDirectory()+"/cdbhe/1.jpg");
        RetrofitClient.getInstance().executeUploadFile("grpc/api/uploadFile.App",iHttpDemoBiz,this,file);
    }

    @Override
    public void onSuccess(int i, Object o) {
        super.onSuccess(i, o);
    }

    @Override
    public void onResponse(int i, Object o) {
        super.onResponse(i, o);
    }

    @Override
    public void onException(int i, Object o) {
        super.onException(i, o);
    }

    @Override
    public void onError(int i, Throwable throwable) {
        super.onError(i, throwable);
    }
}
