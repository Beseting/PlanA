package com.cdbhe.plib.http.retrofit;

import com.cdbhe.plib.http.common.RxManager;
import com.cdbhe.plib.http.model.Data;
import com.cdbhe.plib.utils.DateUtils;
import com.cdbhe.plib.utils.FileUtils;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Kevin on 2018/3/6.
 */

public class RetrofitClient {
    private static final int DEFAULT_TIMEOUT = 10;
    private ApiService apiService;
    private OkHttpClient okHttpClient;
    private static String BASE_URL = "https://www.baidu.com/";

    public static void initBaseUrl(String baseUrl){
        BASE_URL = baseUrl;
    }

    private static class SingletonHolder {
        private static RetrofitClient INSTANCE = new RetrofitClient();
    }

    public static RetrofitClient getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private RetrofitClient() {
        okHttpClient = new OkHttpClient.Builder()
                .addNetworkInterceptor(
                        new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public void cancelRequest(String taskId){
        RxManager.getInstance().cancel(taskId);
    }

    /** -----------------------------------------------------  Encapsulation  -------------------------------------------------------------------- **/

    /**
     * 响应Entity 不带requestCode
     * @param observable
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String entityEncapsulation(Observable<Data> observable,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Data>(iBaseBiz.getActivity(),commonHttpCallback,taskId));
        return taskId;
    }

    /**
     * 响应Entity 带requestCode
     * @param observable
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String entityEncapsulation(Observable<Data> observable,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<Data>(iBaseBiz.getActivity(),requestCode,commonHttpCallback,taskId));
        return taskId;
    }

    /**
     * 响应ResponseBody 不带requestCode
     * @param observable
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String responseBodyEncapsulation(Observable<ResponseBody> observable,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResponseBody>(iBaseBiz.getActivity(),commonHttpCallback,taskId));
        return taskId;
    }

    /**
     * 响应ResponseBody 带requestCode
     * @param observable
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String responseBodyEncapsulation(Observable<ResponseBody> observable,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new HttpObserver<ResponseBody>(iBaseBiz.getActivity(),requestCode,commonHttpCallback,taskId));
        return taskId;
    }

    /** ---------------------------------------------------------  GET  --------------------------------------------------------------------------------------------- **/

    /**
     * GET请求 响应Entity 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doGet(String url,IBaseBiz iBaseBiz, CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doGet(url),iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应Entity 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doGet(String url,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doGet(url,params),iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应Entity 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doGet(String url,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doGet(url),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应Entity 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doGet(String url,int requestCode,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doGet(url,params),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executeGet(String url,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executeGet(url),iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executeGet(String url,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executeGet(url,params),iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executeGet(String url,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executeGet(url),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * GET请求 响应responseBody 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executeGet(String url,int requestCode,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executeGet(url,params),requestCode,iBaseBiz,commonHttpCallback);
    }

    /** --------------------------------------------------------  POST  ------------------------------------------------------------------------- **/

    /**
     * POST请求 响应Entity 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doPost(String url,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doPost(url),iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应Entity 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doPost(String url,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doPost(url,params),iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应Entity 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doPost(String url,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doPost(url),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应Entity 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String doPost(String url,int requestCode,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback){
        return entityEncapsulation(apiService.doPost(url,params),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executePost(String url,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executePost(url),iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executePost(String url,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executePost(url,params),iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executePost(String url,int requestCode,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executePost(url),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * POST请求 响应responseBody 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @return
     */
    public String executePost(String url,int requestCode,Map<String,Object> params,IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback){
        return responseBodyEncapsulation(apiService.executePost(url,params),requestCode,iBaseBiz,commonHttpCallback);
    }

    /** ---------------------------------------------------------  UploadFile  ----------------------------------------------------------------------------- **/

    /**
     * 单个文件上传 响应Entity 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,File file){
        return entityEncapsulation(apiService.uploadFile(url, FileUtils.getUploadFilePart(file)),iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,File file){
        return entityEncapsulation(apiService.uploadFile(url,params,FileUtils.getUploadFilePart(file)),iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url,int requestCode, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,File file){
        return entityEncapsulation(apiService.uploadFile(url,FileUtils.getUploadFilePart(file)),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应Entity 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String uploadFile(String url,int requestCode,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,File file){
        return entityEncapsulation(apiService.uploadFile(url,params,FileUtils.getUploadFilePart(file)),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,File file){
        return responseBodyEncapsulation(apiService.executeUploadFile(url,FileUtils.getUploadFilePart(file)),iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,File file){
        return responseBodyEncapsulation(apiService.executeUploadFile(url,params,FileUtils.getUploadFilePart(file)),iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url,int requestCode, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,File file){
        return responseBodyEncapsulation(apiService.executeUploadFile(url,FileUtils.getUploadFilePart(file)),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 单个文件上传 响应ResponseBody 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param file
     * @return
     */
    public String executeUploadFile(String url,int requestCode,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,File file){
        return responseBodyEncapsulation(apiService.executeUploadFile(url,params,FileUtils.getUploadFilePart(file)),requestCode,iBaseBiz,commonHttpCallback);
    }

    /** ----------------------------------------------------------- uploadFiles ------------------------------------------------- **/

    /**
     * 多个文件上传 响应Entity 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url,maps),iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url,params,maps),iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url,int requestCode, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url,maps),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应Entity 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String uploadFiles(String url,int requestCode,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<Data> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return entityEncapsulation(apiService.uploadFiles(url,params,maps),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 无参数
     * @param url
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url,maps),iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 有参数
     * @param url
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url,params,maps),iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 无参数 带requestCode
     * @param url
     * @param requestCode
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url,int requestCode, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url,maps),requestCode,iBaseBiz,commonHttpCallback);
    }

    /**
     * 多个文件上传 响应ResponseBody 有参数 带requestCode
     * @param url
     * @param requestCode
     * @param params
     * @param iBaseBiz
     * @param commonHttpCallback
     * @param fileMaps
     * @return
     */
    public String executeUploadFiles(String url,int requestCode,Map<String,Object> params, IBaseBiz iBaseBiz,CommonHttpCallback<ResponseBody> commonHttpCallback,Map<String, File> fileMaps){
        Map<String,MultipartBody.Part> maps = new HashMap<>();
        for(String key:fileMaps.keySet()){
            maps.put(key,FileUtils.getUploadFilePart(fileMaps.get(key)));
        }
        return responseBodyEncapsulation(apiService.executeUploadFiles(url,params,maps),requestCode,iBaseBiz,commonHttpCallback);
    }
}
