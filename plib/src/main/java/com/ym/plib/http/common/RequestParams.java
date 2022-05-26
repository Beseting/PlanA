package com.ym.plib.http.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2019/1/4.
 */

public class RequestParams {
    private Map<String,Object> paramMap;

    public RequestParams() {
        this.paramMap = new HashMap<>();
    }

    public static RequestParams getInstance(){
        return new RequestParams();
    }

    public RequestParams add(String key,Object value){
        this.paramMap.put(key,value);
        return this;
    }

    public Map<String,Object> getParamMap(){
        return paramMap;
    }
}
