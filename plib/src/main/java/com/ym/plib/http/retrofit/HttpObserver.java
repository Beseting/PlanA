package com.ym.plib.http.retrofit;

import com.ym.plib.http.common.RxManager;

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
        if (this.taskId != null)//取消请求任务，解除持有，避免内存泄漏
            RetrofitClient.getInstance().cancelRequest(taskId);
    }

    @Override
    public void onError(Throwable e) {
        resCallback.onError(e);
    }

    @Override
    public void onComplete() {
    }
}
