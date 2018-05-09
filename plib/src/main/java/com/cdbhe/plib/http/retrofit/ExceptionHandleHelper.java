package com.cdbhe.plib.http.retrofit;

import android.net.ParseException;

import com.google.gson.JsonParseException;

import org.apache.http.conn.ConnectTimeoutException;
import org.json.JSONException;

import java.net.ConnectException;

import retrofit2.HttpException;

/**
 * Created by Kevin on 2018/3/6.
 */

public class ExceptionHandleHelper {
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int REQUEST_TIMEOUT = 408;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;
    public static String getExceptionMsg(Throwable e) {
        if (e instanceof HttpException) {
            String exceptionMsg;
            HttpException httpException = (HttpException) e;
            switch (httpException.code()) {
                case UNAUTHORIZED:
                    exceptionMsg = "code:401,msg:未授权";
                    break;
                case FORBIDDEN:
                    exceptionMsg = "code:403,msg:禁止访问";
                    break;
                case NOT_FOUND:
                    exceptionMsg  = "code:404,msg:请求未找到";
                    break;
                case REQUEST_TIMEOUT:
                    exceptionMsg = "code:408,msg:请求超时";
                    break;
                case GATEWAY_TIMEOUT:
                    exceptionMsg = "code:504,msg:请求超时";
                    break;
                case INTERNAL_SERVER_ERROR:
                    exceptionMsg = "code:500,msg:服务器错误";
                    break;
                case BAD_GATEWAY:
                    exceptionMsg = "code:502,msg:无效网关";
                    break;
                case SERVICE_UNAVAILABLE:
                    exceptionMsg = "code:503,msg:服务器异常";
                    break;
                default:
                    exceptionMsg = "网络异常";
                    break;
            }
            return exceptionMsg;
        } else if (e instanceof JsonParseException
                || e instanceof JSONException
                || e instanceof ParseException
                || e instanceof com.google.gson.stream.MalformedJsonException) {
            return "解析错误";
        } else if (e instanceof ConnectException) {
            return "连接失败";
        } else if (e instanceof javax.net.ssl.SSLHandshakeException) {
            return "证书验证失败";
        } else if (e instanceof ConnectTimeoutException) {
            return "连接超时";
        } else if (e instanceof java.net.SocketTimeoutException) {
            return "连接超时";
        } else {
            return "未知错误";
        }
    }
}
