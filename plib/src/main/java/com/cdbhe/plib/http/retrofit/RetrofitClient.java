package com.cdbhe.plib.http.retrofit;

import com.cdbhe.plib.http.common.RxManager;
import com.cdbhe.plib.http.gson.DataTypeAdaptor;
import com.cdbhe.plib.http.model.Data;
import com.cdbhe.plib.utils.DateUtils;
import com.cdbhe.plib.utils.FileUtils;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 2018/3/6.
 */

public class RetrofitClient<T> {
    private static final int DEFAULT_TIMEOUT = 10;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    private static String BASE_URL = "https://www.baidu.com/";

    public static void initBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapterFactory(DataTypeAdaptor.FACTORY);
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void cancelRequest(String taskId) {
        RxManager.getInstance().cancel(taskId);
    }

    /** -----------------------------------------------------  Encapsulation  -------------------------------------------------------------------- **/

    /**
     * 响应Entity 不带requestCode
     *
     * @param observable
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String entityEncapsulation(Observable<Data> observable, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Data>(iBaseBiz.getActivity(), ICommonHttpCallback, taskId));
        return taskId;
    }

    /**
     * 响应Entity 带requestCode
     *
     * @param observable
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String entityEncapsulation(Observable<Data> observable, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Data>(iBaseBiz.getActivity(), requestCode, ICommonHttpCallback, taskId));
        return taskId;
    }

    /**
     * 响应ResponseBody 不带requestCode
     *
     * @param observable
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String responseBodyEncapsulation(Observable<ResponseBody> observable, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResponseBody>(iBaseBiz.getActivity(), ICommonHttpCallback, taskId));
        return taskId;
    }

    /**
     * 响应ResponseBody 带requestCode
     *
     * @param observable
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String responseBodyEncapsulation(Observable<ResponseBody> observable, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResponseBody>(iBaseBiz.getActivity(), requestCode, ICommonHttpCallback, taskId));
        return taskId;
    }

    /** ---------------------------------------------------------  GET  --------------------------------------------------------------------------------------------- **/

    /**
     * GET请求 响应Entity 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doGet(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doGet(url), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应Entity 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doGet(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doGet(url, params), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应Entity 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doGet(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doGet(url), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应Entity 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doGet(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doGet(url, params), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executeGet(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executeGet(url), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executeGet(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executeGet(url, params), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executeGet(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executeGet(url), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executeGet(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executeGet(url, params), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /** --------------------------------------------------------  POST  ------------------------------------------------------------------------- **/

    /**
     * POST请求 响应Entity 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doPost(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doPost(url), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应Entity 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doPost(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doPost(url, params), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应Entity 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doPost(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doPost(url), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应Entity 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String doPost(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return entityEncapsulation(apiService.doPost(url, params), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executePost(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executePost(url), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executePost(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executePost(url, params), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executePost(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executePost(url), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executePost(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(apiService.executePost(url, params), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /** ---------------------------------------------------------  UploadFile  ----------------------------------------------------------------------------- **/

    /**
     * 单个文件上传 响应Entity 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return entityEncapsulation(apiService.uploadFile(url, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return entityEncapsulation(apiService.uploadFile(url, params, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return entityEncapsulation(apiService.uploadFile(url, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return entityEncapsulation(apiService.uploadFile(url, params, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return responseBodyEncapsulation(apiService.executeUploadFile(url, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return responseBodyEncapsulation(apiService.executeUploadFile(url, params, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return responseBodyEncapsulation(apiService.executeUploadFile(url, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, File file) {
        return responseBodyEncapsulation(apiService.executeUploadFile(url, params, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /** ----------------------------------------------------------- uploadFiles ------------------------------------------------- **/

    /**
     * 多个文件上传 响应Entity 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url, maps), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url, params, maps), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url, maps), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url, params, maps), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 无参数
     *
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url, maps), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 有参数
     *
     * @param url
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url, params, maps), iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 无参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url, int requestCode, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url, maps), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 有参数 带requestCode
     *
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url, int requestCode, Map<String, Object> params, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback, Map<String, File> fileMaps) {
        Map<String, MultipartBody.Part> maps = new HashMap<>();
        for (String key : fileMaps.keySet()) {
            maps.put(key, FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url, params, maps), requestCode, iBaseBiz, ICommonHttpCallback);
    }
}
