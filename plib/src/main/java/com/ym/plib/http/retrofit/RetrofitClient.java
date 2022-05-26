package com.ym.plib.http.retrofit;

import com.ym.plib.http.common.RxManager;
import com.ym.plib.http.request.DownloadRequest;
import com.ym.plib.http.request.GetRequest;
import com.ym.plib.http.request.PostRequest;
import com.ym.plib.http.request.UploadRequest;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Kevin on 2018/3/6.
 */

public class RetrofitClient {
    private HttpConfig httpConfig = null; // Http基础配置
    private ApiService apiService;
    private RequestInterceptor requestInterceptor = null;//请求拦截器

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void init(HttpConfig httpConfig) {
        this.init(httpConfig, null);
    }

    public void init(HttpConfig httpConfig, Map<String, String> commonHeaderMap) {
        this.httpConfig = httpConfig;
        this.requestInterceptor = new RequestInterceptor(commonHeaderMap);
        this.initRetrofit();
    }

    /**
     * 请求拦截器，修改请求header
     */
    private class RequestInterceptor implements Interceptor {
        private Map<String, String> commonHeaderMap;

        public RequestInterceptor(Map<String, String> commonHeaderMap) {
            this.commonHeaderMap = commonHeaderMap;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();
            if (commonHeaderMap != null) {
                for (Map.Entry<String, String> entry : commonHeaderMap.entrySet()) {
                    builder.addHeader(entry.getKey(), entry.getValue());
                }
            }
            return chain.proceed(builder.build());
        }
    }

    private void initRetrofit() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(httpConfig.getDefaultConnectTimeout(), TimeUnit.MILLISECONDS)
                .readTimeout(httpConfig.getDefaultReadTimeout(), TimeUnit.MILLISECONDS)
                .writeTimeout(httpConfig.getDefaultWriteTimeout(), TimeUnit.MILLISECONDS)
                .addInterceptor(requestInterceptor)
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
     *
     * @param taskId
     */
    public void cancelRequest(String taskId) {
        RxManager.getInstance().cancel(taskId);
    }

    /**
     * 取消所有网络请求
     */
    public void cancelAll() {
        RxManager.getInstance().cancelAll();
    }

    /**
     * Get请求
     *
     * @param url 相对路径
     * @return GetRequest对象
     */
    public GetRequest get(String url) {
        return new GetRequest(url, apiService);
    }

    /**
     * Post请求
     *
     * @param url 相对路径
     * @return PostRequest对象
     */
    public PostRequest post(String url) {
        return new PostRequest(url, apiService);
    }

    public UploadRequest upload(String url) {
        return new UploadRequest(url, apiService);
    }

    public DownloadRequest download(String url) {
        return new DownloadRequest(url, apiService);
    }
}
