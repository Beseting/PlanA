package com.cdbhe.plib.http.retrofit;

/**
 * Created by Kevin on 2018/3/6.
 */

public interface ICommonHttpCallback<T> {
    // 回调成功，不区分status全返回
    void onResponse(int requestCode,T data);
    // 回调正确状态的data数据
    void onSuccess(int requestCode,T data);
    // 回调异常状态data数据
    void onException(int requestCode,T data);
    // 回调错误方法
    void onError(int requestCode,Throwable e);
}
