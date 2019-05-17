package com.cdbhe.plib.http.retrofit;

/**
 * Created by Kevin on 2018/3/6.
 */

public interface ResCallback<T>{
    /**
     * 回调成功
     * @param t
     */
    void onResponse(T t);

    /**
     * 回调错误方法
     * @param e
     */
    void onError(Throwable e);
}
