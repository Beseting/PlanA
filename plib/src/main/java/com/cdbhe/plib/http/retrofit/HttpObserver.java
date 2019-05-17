package com.cdbhe.plib.http.retrofit;

import com.cdbhe.plib.http.common.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import okhttp3.ResponseBody;

/**
 * Created by Kevin on 2019/5/13.
 */

public class HttpObserver implements Observer<ResponseBody> {
    private String taskId;
    private ResCallback resCallback;

    public HttpObserver(ResCallback resCallback, String taskId) {
        this.resCallback = resCallback;
        this.taskId = taskId;
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxManager.getInstance().add(taskId, d);
    }

    @Override
    public void onNext(ResponseBody responseBody) {
        resCallback.onResponse(responseBody);
    }

    @Override
    public void onError(Throwable e) {
        resCallback.onError(e);
    }

    @Override
    public void onComplete() {
    }
}
