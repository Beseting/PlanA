package com.cdbhe.plib.http.retrofit;


import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * Created by Kevin on 2018/3/5.
 */

public interface ApiService {

    /**
     * Get请求 常规参数
     *
     * @param headers
     * @param url
     * @param params
     * @return
     */
    @GET("{url}")
    Observable<ResponseBody> get(@HeaderMap Map<String, Object> headers, @Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> params);

    /**
     * Get请求 Json参数
     *
     * @param headers
     * @param url
     * @param body
     * @return
     */
    @GET("{url}")
    Observable<ResponseBody> get(@HeaderMap Map<String, Object> headers, @Path(value = "url", encoded = true) String url, @Body RequestBody body);

    /**
     * Post请求 常规参数
     *
     * @param headers
     * @param url
     * @param params
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> post(@HeaderMap Map<String, Object> headers, @Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> params);

    /**
     * Post请求 Json参数
     *
     * @param headers
     * @param url
     * @param body
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> post(@HeaderMap Map<String, Object> headers, @Path(value = "url", encoded = true) String url, @Body RequestBody body);

    /**
     * 文件上传 常规参数
     * @param headers
     * @param url
     * @param params
     * @param filePart
     * @return
     */
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> uploadFile(@HeaderMap Map<String, Object> headers,@Path(value = "url", encoded = true) String url,@QueryMap Map<String, Object> params,@Part MultipartBody.Part filePart);

    /**
     * 文件上传 Json参数
     * @param headers
     * @param url
     * @param body
     * @param filePart
     * @return
     */
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> uploadFile(@HeaderMap Map<String, Object> headers,@Path(value = "url", encoded = true) String url,@Body RequestBody body,@Part MultipartBody.Part filePart);

    /**
     * 文件下载
     * @param url
     * @return
     */
    @Streaming
    @GET("{url}")
    Observable<ResponseBody> downloadFile(@Path(value = "url", encoded = true) String url);
}
