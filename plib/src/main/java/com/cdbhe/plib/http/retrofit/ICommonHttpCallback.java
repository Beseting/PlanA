package com.cdbhe.plib.http.retrofit;

/**
 * Created by Kevin on 2018/3/6.
 */

public interface ICommonHttpCallback<T> {
    void onSuccess(int requestCode,T data);
    void onError(int requestCode,Throwable e);
}
