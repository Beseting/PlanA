package com.cdbhe.plana.httpdemo;

import android.content.Context;

import com.cdbhe.plib.http.common.RequestParams;
import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.utils.LogUtils;

public class HttpDemoPresenter extends CommonRequestCallback{
    private IHttpDemoBiz iHttpDemoBiz;
    public HttpDemoPresenter(IHttpDemoBiz iHttpDemoBiz) {
        super(iHttpDemoBiz.getActivity());
        this.iHttpDemoBiz = iHttpDemoBiz;
    }

    public void doRequest1(){
        RequestParams.getInstance().addParam("token", "18383930457");
        RetrofitClient.getInstance().executeGet("api/configure/findEmployee",1,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest2(){
        RequestParams.getInstance().addParam("token", "18383930457");
        RetrofitClient.getInstance().executeGet("api/configure/findEmployeeExtend",2,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest3(){
        RequestParams.getInstance().addParam("token", "18383930457").addParam("version",0);
        RetrofitClient.getInstance().executeGet("api/configure/queryDevices",3,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest4(){
        RequestParams.getInstance().addParam("token", "18383930457").addParam("version",0);
        RetrofitClient.getInstance().executeGet("api/configure/queryDeviceTypes",4,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest5(){
        RequestParams.getInstance().addParam("token", "18383930457").addParam("version",0);
        RetrofitClient.getInstance().executeGet("api/configure/queryProblemTypes",5,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest6(){
        RequestParams.getInstance().addParam("token", "18383930457").addParam("version",0);
        RetrofitClient.getInstance().executeGet("api/configure/queryDeviceProblems",6,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest7(){
        RequestParams.getInstance().addParam("token", "18383930457").addParam("version",0);
        RetrofitClient.getInstance().executeGet("api/configure/queryEmployees",7,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    @Override
    public void onSuccess(int i, Object o) {
        super.onSuccess(i, o);
    }

    @Override
    public void onResponse(int i, Object o) {
        super.onResponse(i, o);
        switch (i){
            case 1:
                LogUtils.d("request1-->"+o);
                break;
            case 2:
                LogUtils.d("request2-->"+o);
                break;
            case 3:
                LogUtils.d("request3-->"+o);
                break;
            case 4:
                LogUtils.d("request4-->"+o);
                break;
            case 5:
                LogUtils.d("request5-->"+o);
                break;
            case 6:
                LogUtils.d("request6-->"+o);
                break;
            case 7:
                LogUtils.d("request7-->"+o);
                break;
        }
    }
}
