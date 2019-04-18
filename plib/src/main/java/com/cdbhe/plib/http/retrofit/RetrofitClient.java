package com.cdbhe.plib.http.retrofit;

import com.cdbhe.plib.http.common.RxManager;
import com.cdbhe.plib.http.gson.DataTypeAdaptor;
import com.cdbhe.plib.http.model.Data;
import com.cdbhe.plib.utils.DateUtils;
import com.cdbhe.plib.utils.FileUtils;
import com.google.gson.GsonBuilder;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 2018/3/6.
 */

public class RetrofitClient<T> {
    private static long DEFAULT_TIMEOUT = 12;
    private ApiService apiService;
    private static String BASE_URL = "https://www.baidu.com/";
    private static Map<String,String> HEADERS = null;

    /**
     * 初始化BaseUrl 需要在Application中初始化
     * @param baseUrl
     */
    public static void initBaseUrl(String baseUrl) {
        BASE_URL = baseUrl;
    }

    /**
     * 设置Header
     * @param headers
     */
    public static void setHeaders(Map<String,String> headers) {
        HEADERS = headers;
    }

    /**
     * 设置超时时间，需要在Application中设置
     * @param timeout
     */
    public static void setDefaultTimeout(long timeout){
        DEFAULT_TIMEOUT = timeout;
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
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gsonBuilder.create()))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
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
                .unsubscribeOn(Schedulers.io())
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
                .unsubscribeOn(Schedulers.io())
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
                .unsubscribeOn(Schedulers.io())
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
        return entityEncapsulation(HEADERS==null?apiService.doGet(url):apiService.doGet(HEADERS,url), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doGet(url, params):apiService.doGet(HEADERS,url, params), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doGet(url):apiService.doGet(HEADERS,url), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doGet(url, params):apiService.doGet(HEADERS,url, params), requestCode, iBaseBiz, ICommonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 无参数
     *executeGet
     * @param url
     * @param iBaseBiz
     * @param ICommonHttpCallback
     * @return
     */
    public String executeGet(String url, IBaseBiz iBaseBiz, ICommonHttpCallback<T> ICommonHttpCallback) {
        return responseBodyEncapsulation(HEADERS==null?apiService.executeGet(url):apiService.executeGet(HEADERS,url), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeGet(url, params):apiService.executeGet(HEADERS,url, params), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeGet(url):apiService.executeGet(HEADERS,url), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeGet(url, params):apiService.executeGet(HEADERS,url, params), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doPost(url):apiService.doPost(HEADERS,url), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doPost(url, params):apiService.doPost(HEADERS,url, params), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doPost(url):apiService.doPost(HEADERS,url), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.doPost(url, params):apiService.doPost(HEADERS,url, params), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executePost(url):apiService.executePost(HEADERS,url), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executePost(url, params):apiService.executePost(HEADERS,url, params), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executePost(url):apiService.executePost(HEADERS,url), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executePost(url, params):apiService.executePost(HEADERS,url, params), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFile(url, FileUtils.getUploadFilePart(file)):apiService.uploadFile(HEADERS,url, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFile(url, params, FileUtils.getUploadFilePart(file)):apiService.uploadFile(HEADERS,url, params, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFile(url, FileUtils.getUploadFilePart(file)):apiService.uploadFile(HEADERS,url, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFile(url, params, FileUtils.getUploadFilePart(file)):apiService.uploadFile(HEADERS,url, params, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFile(url, FileUtils.getUploadFilePart(file)):apiService.executeUploadFile(HEADERS,url, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFile(url, params, FileUtils.getUploadFilePart(file)):apiService.executeUploadFile(HEADERS,url, params, FileUtils.getUploadFilePart(file)), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFile(url, FileUtils.getUploadFilePart(file)):apiService.executeUploadFile(HEADERS,url, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFile(url, params, FileUtils.getUploadFilePart(file)):apiService.executeUploadFile(HEADERS,url, params, FileUtils.getUploadFilePart(file)), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFiles(url, maps):apiService.uploadFiles(HEADERS,url, maps), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFiles(url, params, maps):apiService.uploadFiles(HEADERS,url, params, maps), iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFiles(url, maps):apiService.uploadFiles(HEADERS,url, maps), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return entityEncapsulation(HEADERS==null?apiService.uploadFiles(url, params, maps):apiService.uploadFiles(HEADERS,url, params, maps), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFiles(url, maps):apiService.executeUploadFiles(HEADERS,url, maps), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFiles(url, params, maps):apiService.executeUploadFiles(HEADERS,url, params, maps), iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFiles(url, maps):apiService.executeUploadFiles(HEADERS,url, maps), requestCode, iBaseBiz, ICommonHttpCallback);
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
        return responseBodyEncapsulation(HEADERS==null?apiService.executeUploadFiles(url, params, maps):apiService.executeUploadFiles(HEADERS,url, params, maps), requestCode, iBaseBiz, ICommonHttpCallback);
    }
}
