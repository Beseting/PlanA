package com.cdbhe.plib.http.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2019/1/4.
 */

public class RequestParams {
    public static Map<String,Object> paramMap;
    public static RequestParams getInstance(){
        paramMap = new HashMap<>();
        return new RequestParams();
    }

    public RequestParams addParam(String key, Object objVal){
        paramMap.put(key,objVal);
        return this;
    }
}
