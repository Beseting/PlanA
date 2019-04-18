package com.cdbhe.plib.http.retrofit;


import com.cdbhe.plib.http.model.Data;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.HeaderMap;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.QueryMap;

/**
 * Created by Kevin on 2018/3/5.
 */

public interface ApiService {

    /** ---------------------------------------  GET  ------------------------------------------ **/

    /**
     * GET请求 无参
     *
     * @param url
     * @return Data
     **/
    @GET("{url}")
    Observable<Data> doGet(@Path(value = "url", encoded = true) String url);

    /**
     * GET请求 无参 headers
     * @param headers
     * @param url
     * @return
     */
    @GET("{url}")
    Observable<Data> doGet(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url);

    /**
     * GET请求 有参
     *
     * @param url
     * @param maps
     * @return Data
     **/
    @GET("{url}")
    Observable<Data> doGet(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * GET请求 有参 headers
     *
     * @param url
     * @param maps
     * @return Data
     **/
    @GET("{url}")
    Observable<Data> doGet(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * GET请求 无参
     *
     * @param url
     * @return ResponseBody
     **/
    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url);

    /**
     * GET请求 无参 headers
     *
     * @param url
     * @return ResponseBody
     **/
    @GET("{url}")
    Observable<ResponseBody> executeGet(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url);

    /**
     * GET请求 有参
     *
     * @param url
     * @param maps
     * @return ResponseBody
     **/
    @GET("{url}")
    Observable<ResponseBody> executeGet(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * GET请求 有参 headers
     *
     * @param url
     * @param maps
     * @return ResponseBody
     **/
    @GET("{url}")
    Observable<ResponseBody> executeGet(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);


    /** --------------------------------------  POST  ------------------------------------------ **/

    /**
     * POST请求 无参
     *
     * @param url 地址
     * @return Data
     **/
    @POST("{url}")
    Observable<Data> doPost(@Path(value = "url", encoded = true) String url);

    /**
     * POST请求 无参 headers
     *
     * @param url 地址
     * @return Data
     **/
    @POST("{url}")
    Observable<Data> doPost(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url);

    /**
     * POST请求 有参
     *
     * @param url  地址
     * @param maps 参数 键值对
     * @return Data
     **/
    @POST("{url}")
    Observable<Data> doPost(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * POST请求 有参 headers
     *
     * @param url  地址
     * @param maps 参数 键值对
     * @return Data
     **/
    @POST("{url}")
    Observable<Data> doPost(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * POST请求 无参
     *
     * @param url 地址
     * @return ResponseBody
     **/
    @POST("{url}")
    Observable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url);

    /**
     * POST请求 无参 headers
     *
     * @param headers
     * @param url 地址
     * @return ResponseBody
     **/
    @POST("{url}")
    Observable<ResponseBody> executePost(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url);

    /**
     * POST请求 有参
     *
     * @param url  地址
     * @param maps 参数 键值对
     * @return ResponseBody
     **/
    @POST("{url}")
    Observable<ResponseBody> executePost(@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);

    /**
     * POST请求 有参 headers
     *
     * @param headers
     * @param url  地址
     * @param maps 参数 键值对
     * @return ResponseBody
     **/
    @POST("{url}")
    Observable<ResponseBody> executePost(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url, @QueryMap Map<String, Object> maps);


    /** -----------------------------------  File Upload  -------------------------------------- **/

    /**
     * 单个文件上传 无参
     *
     * @param url  地址
     * @param file 文件
     **/
    @Multipart
    @POST("{url}")
    Observable<Data> uploadFile(@Path(value = "url", encoded = true) String url,
                                @Part MultipartBody.Part file);

    /**
     * 单个文件上传 无参 headers
     *
     * @param url  地址
     * @param file 文件
     **/
    @Multipart
    @POST("{url}")
    Observable<Data> uploadFile(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url,
                                @Part MultipartBody.Part file);

    /**
     * 单个文件上传 有参
     *
     * @param url  地址
     * @param file 文件
     * @param maps 参数
     **/
    @Multipart
    @POST("{url}")
    Observable<Data> uploadFile(@Path(value = "url", encoded = true) String url,
                                @QueryMap Map<String, Object> maps,
                                @Part MultipartBody.Part file);

    /**
     * 单个文件上传 有参 headers
     *
     * @param url  地址
     * @param file 文件
     * @param maps 参数
     **/
    @Multipart
    @POST("{url}")
    Observable<Data> uploadFile(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url,
                                @QueryMap Map<String, Object> maps,
                                @Part MultipartBody.Part file);

    /**
     * 单个文件上传 无参
     *
     * @param url  地址
     * @param file 文件
     **/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> executeUploadFile(@Path(value = "url", encoded = true) String url,
                                               @Part MultipartBody.Part file);

    /**
     * 单个文件上传 无参 headers
     *
     * @param url  地址
     * @param file 文件
     **/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> executeUploadFile(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url,
                                               @Part MultipartBody.Part file);

    /**
     * 单个文件上传 有参
     *
     * @param url  地址
     * @param file 文件
     * @param maps 参数
     **/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> executeUploadFile(@Path(value = "url", encoded = true) String url,
                                               @QueryMap Map<String, Object> maps, @Part MultipartBody.Part file);

    /**
     * 单个文件上传 有参 headers
     *
     * @param url  地址
     * @param file 文件
     * @param maps 参数
     **/
    @Multipart
    @POST("{url}")
    Observable<ResponseBody> executeUploadFile(@HeaderMap Map<String,String> headers,@Path(value = "url", encoded = true) String url,
                                               @QueryMap Map<String, Object> maps, @Part MultipartBody.Part file);

    /**
     * 多个文件/图片上传 无参
     *
     * @param url  地址
     * @param maps 参数 键值对
     **/
    @POST("{url}")
    Observable<Data> uploadFiles(@Path("url") String url, @PartMap() Map<String, MultipartBody.Part> maps);

    /**
     * 多个文件/图片上传 无参 headers
     *
     * @param url  地址
     * @param maps 参数 键值对
     **/
    @POST("{url}")
    Observable<Data> uploadFiles(@HeaderMap Map<String,String> headers,@Path("url") String url, @PartMap() Map<String, MultipartBody.Part> maps);

    /**
     * 多个文件/图片上传 有参
     *
     * @param url
     * @param maps
     * @param partMaps
     * @return
     */
    @POST("{url}")
    Observable<Data> uploadFiles(@Path("url") String url,
                                 @QueryMap Map<String, Object> maps,
                                 @PartMap() Map<String, MultipartBody.Part> partMaps);

    /**
     * 多个文件/图片上传 有参 headers
     *
     * @param url
     * @param maps
     * @param partMaps
     * @return
     */
    @POST("{url}")
    Observable<Data> uploadFiles(@HeaderMap Map<String,String> headers,@Path("url") String url,
                                 @QueryMap Map<String, Object> maps,
                                 @PartMap() Map<String, MultipartBody.Part> partMaps);

    /**
     * 多个文件/图片上传 无参
     *
     * @param url  地址
     * @param maps 参数 键值对
     **/
    @POST("{url}")
    Observable<ResponseBody> executeUploadFiles(@Path("url") String url, @PartMap() Map<String, MultipartBody.Part> maps);

    /**
     * 多个文件/图片上传 无参 headers
     *
     * @param url  地址
     * @param maps 参数 键值对
     **/
    @POST("{url}")
    Observable<ResponseBody> executeUploadFiles(@HeaderMap Map<String,String> headers,@Path("url") String url, @PartMap() Map<String, MultipartBody.Part> maps);

    /**
     * 多个文件/图片上传 有参
     *
     * @param url
     * @param maps
     * @param partMaps
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> executeUploadFiles(@Path("url") String url,
                                                @QueryMap Map<String, Object> maps,
                                                @PartMap() Map<String, MultipartBody.Part> partMaps);

    /**
     * 多个文件/图片上传 有参 headers
     *
     * @param url
     * @param maps
     * @param partMaps
     * @return
     */
    @POST("{url}")
    Observable<ResponseBody> executeUploadFiles(@HeaderMap Map<String,String> headers,@Path("url") String url,
                                                @QueryMap Map<String, Object> maps,
                                                @PartMap() Map<String, MultipartBody.Part> partMaps);
}
