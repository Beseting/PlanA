package com.cdbhe.plib.http.retrofit;

import com.cdbhe.plib.http.common.RxManager;
import com.cdbhe.plib.http.request.DownloadRequest;
import com.cdbhe.plib.http.request.GetRequest;
import com.cdbhe.plib.http.request.PostRequest;
import com.cdbhe.plib.http.request.UploadRequest;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Kevin on 2018/3/6.
 */

public class RetrofitClient {
    private HttpConfig httpConfig = null; // Http基础配置
    private ApiService apiService;

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(HttpConfig httpConfig){
        this.httpConfig = httpConfig;
        this.initRetrofit();
    }

    private void initRetrofit(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(httpConfig.getDefaultConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.getDefaultReadTimeout(),TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.getDefaultWriteTimeout(),TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(httpConfig.getBaseUrl())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    /**
     * 取消目标网络请求
     * @param taskId
     */
    public void cancelRequest(String taskId) {
        RxManager.getInstance().cancel(taskId);
    }

    /**
     * 取消所有网络请求
     */
    public void cancelAll(){
        RxManager.getInstance().cancelAll();
    }

    /**
     * Get请求
     * @param url 相对路径
     * @return GetRequest对象
     */
    public GetRequest get(String url) {
        return new GetRequest(url,apiService);
    }

    /**
     * Post请求
     * @param url 相对路径
     * @return PostRequest对象
     */
    public PostRequest post(String url){
        return new PostRequest(url,apiService);
    }

    public UploadRequest upload(String url){
        return new UploadRequest(url,apiService);
    }

    public DownloadRequest download(String url){
        return new DownloadRequest(url,apiService);
    }
}
