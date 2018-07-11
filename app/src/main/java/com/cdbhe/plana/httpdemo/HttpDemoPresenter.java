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
        RequestParams.getInstance().addParam("pigeonholeKindID", 0).addParam("Action","SearchUserCCReceiptDocList")
        .addParam("code","").addParam("pageIndex",1).addParam("pageSize",20).addParam("EmployeeID",1)
        .addParam("title","").addParam("kindID",5).addParam("status",1);
        RetrofitClient.getInstance().executeGet("handler/ApiOA.ashx",1,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest2(){
        RequestParams.getInstance().addParam("conferenceTitle", "").addParam("Action","SearchParticipantsNoticeList")
        .addParam("endDate","").addParam("pageIndex",1).addParam("pageSize",20).addParam("EmployeeID",1)
        .addParam("participantsAddress","").addParam("startDate","").addParam("status",999);
        RetrofitClient.getInstance().executeGet("handler/ApiOA.ashx",2,RequestParams.paramMap, iHttpDemoBiz, this);
    }

    public void doRequest3(){
        RequestParams.getInstance().addParam("Action", "SearchNewsReaderList").addParam("pageIndex",1).addParam("pageSize",20)
        .addParam("newsClassificationID",1435).addParam("source","").addParam("EmployeeID",1).addParam("title","").addParam("status",0);
        RetrofitClient.getInstance().executeGet("handler/ApiOA.ashx",3,RequestParams.paramMap, iHttpDemoBiz, this);
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
        }
    }
}
