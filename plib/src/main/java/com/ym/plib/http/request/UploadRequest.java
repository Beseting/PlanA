package com.ym.plib.http.request;

import com.ym.plib.http.file.upload.FileUploadCallback;
import com.ym.plib.http.file.upload.FileUploadObserver;
import com.ym.plib.http.file.upload.UploadFileRequestBody;
import com.ym.plib.http.retrofit.ApiService;
import com.ym.plib.utils.DateUtils;
import com.google.gson.Gson;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class UploadRequest {
    private String url;
    private ApiService apiService;
    private File file = null;
    private Map<String,Object> headers = new HashMap<>();
    private Map<String,Object> params = new HashMap<>();

    public UploadRequest(String url, ApiService apiService) {
        this.url = url;
        this.apiService = apiService;
    }

    public UploadRequest header(String key, Object value){
        this.headers.put(key,value);
        return this;
    }

    public UploadRequest headers(Map<String,Object> headers){
        this.headers.putAll(headers);
        return this;
    }

    public UploadRequest param(String key, Object value){
        params.put(key,value);
        return this;
    }

    public UploadRequest params(Map<String,Object> params){
        this.params.putAll(params);
        return this;
    }

    public UploadRequest upJson(Map<String,Object> params){
        this.headers.put("Content-Type","application/json;charset=utf-8");
        this.params(params);
        return this;
    }

    public UploadRequest upFile(File file){
        this.file = file;
        return this;
    }

    public String execute(FileUploadCallback<ResponseBody> fileUploadCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        FileUploadObserver fileUploadObserver = new FileUploadObserver(taskId,fileUploadCallback);
        UploadFileRequestBody uploadFileRequestBody = new UploadFileRequestBody(file, fileUploadObserver);
        String fileName;
        try {
            fileName = URLEncoder.encode(file.getName(),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            fileName = file.getName();
        }
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", fileName, uploadFileRequestBody);
        if(headers.get("Content-Type")!=null&&headers.get("Content-Type").equals("application/json;charset=utf-8")) { // Json参数
            RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"),new Gson().toJson(params));
            apiService.uploadFile(headers,url,requestBody,part).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(fileUploadObserver);
        }else{ // 常规参数
            apiService.uploadFile(headers,url,params,part).subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(fileUploadObserver);
        }
        return taskId;
    }
}
