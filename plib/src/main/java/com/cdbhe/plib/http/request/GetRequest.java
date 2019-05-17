package com.cdbhe.plib.http.request;

import com.cdbhe.plib.http.retrofit.ApiService;
import com.cdbhe.plib.http.retrofit.HttpObserver;
import com.cdbhe.plib.http.retrofit.ResCallback;
import com.cdbhe.plib.utils.DateUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class GetRequest {
    private String url;
    private ApiService apiService;
    private Map<String,Object> headers = new HashMap<>();
    private Map<String,Object> params = new HashMap<>();

    public GetRequest(String url,ApiService apiService) {
        this.url = url;
        this.apiService = apiService;
    }

    public GetRequest header(String key,Object value){
        this.headers.put(key,value);
        return this;
    }

    public GetRequest headers(Map<String,Object> headers){
        this.headers.putAll(headers);
        return this;
    }

    public GetRequest param(String key,Object value){
        params.put(key,value);
        return this;
    }

    public GetRequest params(Map<String,Object> params){
        this.params.putAll(params);
        return this;
    }

    public GetRequest upJson(Map<String,Object> params){
        this.headers.put("Content-Type","application/json;charset=utf-8");
        this.params(params);
        return this;
    }

    public String execute(ResCallback resCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        if(headers.get("Content-Type")!=null&&headers.get("Content-Type").equals("application/json;charset=utf-8")) { // Json参数
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(params));
            apiService.get(headers,url,requestBody).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpObserver(resCallback, taskId));
        }else{ // 常规参数
            apiService.get(headers,url,params).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new HttpObserver(resCallback, taskId));
        }
        return taskId;
    }
}
