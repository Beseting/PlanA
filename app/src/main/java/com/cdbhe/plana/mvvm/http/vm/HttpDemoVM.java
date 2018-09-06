package com.cdbhe.plana.mvvm.http.vm;

import android.os.Environment;

import com.cdbhe.plana.mvvm.http.callback.CommonRequestCallback;
import com.cdbhe.plana.mvvm.http.biz.IHttpDemoBiz;
import com.cdbhe.plib.http.common.RequestParams;
import com.cdbhe.plib.http.retrofit.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

public class HttpDemoVM extends CommonRequestCallback {
    private IHttpDemoBiz iHttpDemoBiz;
    public HttpDemoVM(IHttpDemoBiz iHttpDemoBiz) {
        super(iHttpDemoBiz.getActivity());
        this.iHttpDemoBiz = iHttpDemoBiz;
    }

    public void doRequest(){
        //请求参数
        RequestParams.getInstance().addParam("key","ab77a6e4370d82e8e78c9baedbd35632").addParam("city",iHttpDemoBiz.getSearchContent());
        //发起请求
        RetrofitClient.getInstance().executeGet("v3/weather/weatherInfo",RequestParams.paramMap,iHttpDemoBiz,this);
    }

    @Override
    public void onResponse(int i, Object o) {
        super.onResponse(i, o);
        //跳至UI 进行数据操作
        iHttpDemoBiz.toActivity(o);
    }

    @Override
    public void onSuccess(int i, Object o) {
        super.onSuccess(i, o);
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
