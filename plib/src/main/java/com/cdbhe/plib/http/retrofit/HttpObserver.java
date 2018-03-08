package com.cdbhe.plib.http.retrofit;

import android.content.Context;

import com.cdbhe.plib.http.common.RxManager;
import com.cdbhe.plib.http.model.Data;
import com.cdbhe.plib.utils.LogUtils;
import com.cdbhe.plib.utils.ToastUtils;

import java.io.IOException;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by Kevin on 2018/3/8.
 */

public class HttpObserver<T> implements Observer<T> {
    private Context mContext;
    private int requestCode = 9527;
    private String taskId;
    private CommonHttpCallback commonHttpCallback;

    public HttpObserver(Context mContext, CommonHttpCallback commonHttpCallback,String taskId) {
        this.mContext = mContext;
        this.commonHttpCallback = commonHttpCallback;
        this.taskId = taskId;
    }

    public HttpObserver(Context mContext, int requestCode, CommonHttpCallback commonHttpCallback,String taskId) {
        this.mContext = mContext;
        this.requestCode = requestCode;
        this.commonHttpCallback = commonHttpCallback;
        this.taskId = taskId;
    }


    @Override
    public void onSubscribe(Disposable d) {
        RxManager.getInstance().add(taskId, d);
    }

    @Override
    public void onNext(T t) {
        if (t != null || !t.equals("")) {
            if (t instanceof Data) {//Entity类型返回
                Data data = (Data) t;
                switch (data.getStatus()) {
                    case 1://正常
                        commonHttpCallback.onSuccess(requestCode, data.getData());
                        break;
                    default://异常
                        ToastUtils.showShort(mContext, data.getMessage());
                        break;
                }
            } else {//ResponseBody类型返回
                ResponseBody responseBody = (ResponseBody) t;
                String result = "";
                if(responseBody!=null && !responseBody.equals("")){
                    try {
                        result = responseBody.source().readUtf8();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                commonHttpCallback.onSuccess(requestCode,result);
            }
        }
    }

    @Override
    public void onError(Throwable e) {
        ToastUtils.showShort(mContext, ExceptionHandleHelper.getExceptionMsg(e));
        LogUtils.e("error:" + e.getMessage());
    }

    @Override
    public void onComplete() {

    }
}
