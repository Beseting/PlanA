package com.cdbhe.plana.mvvm.http.vm;

import com.cdbhe.plana.mvvm.http.biz.IHttpDemoBiz;
import com.cdbhe.plib.http.retrofit.ResCallback;
import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.utils.LogUtils;
import com.cdbhe.plib.utils.ToastUtils;

import java.io.IOException;

import okhttp3.ResponseBody;

public class HttpDemoVM {
    private IHttpDemoBiz iHttpDemoBiz;
    private String taskId;

    public HttpDemoVM(IHttpDemoBiz iHttpDemoBiz) {
        this.iHttpDemoBiz = iHttpDemoBiz;
    }

    public void doRequest() {
        taskId = RetrofitClient.getInstance().get("v3/weather/weatherInfo")
                .param("key", "ab77a6e4370d82e8e78c9baedbd35632")
                .param("city", iHttpDemoBiz.getSearchContent())
                .execute(new ResCallback<ResponseBody>() {
                    @Override
                    public void onResponse(ResponseBody responseBody) {
                        try {
                            iHttpDemoBiz.toActivity(responseBody.string());
                        } catch (IOException e) {
                            iHttpDemoBiz.toActivity("");
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.e(e.getLocalizedMessage());
                    }
                });
    }

    public void cancel() {
        ToastUtils.showShort(iHttpDemoBiz.getActivity(), "网络请求已取消");
        RetrofitClient.getInstance().cancelRequest(taskId);
    }
}