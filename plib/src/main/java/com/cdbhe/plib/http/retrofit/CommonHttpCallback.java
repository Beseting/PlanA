package com.cdbhe.plib.http.retrofit;

/**
 * Created by Kevin on 2018/3/6.
 */

public interface CommonHttpCallback<T> {
    void onSuccess(int requestCode,T data);
}
