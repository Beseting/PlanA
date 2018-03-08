package com.cdbhe.plib.http.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2017/9/5.
 */

public class RequestParams {
    public static RequestParams requestParams;
    public static Map<String,Object> paramMap;
    public static RequestParams getInstance(){
        paramMap = new HashMap<>();
        requestParams = new RequestParams();
        return requestParams;
    }
    public static RequestParams addParam(String key, Object objVal){
        paramMap.put(key,objVal);
        return requestParams;
    }
}
