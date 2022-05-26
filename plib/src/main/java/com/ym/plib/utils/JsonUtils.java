package com.ym.plib.utils;

import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public final class JsonUtils {

    private JsonUtils() {
    }

    /**
     * 对象转换成json字符串
     *
     * @param obj
     * @return
     */
    public static String toJson(Object obj) {
        Gson gson = new Gson();
        return gson.toJson(obj);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Type type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    /**
     * json字符串转成对象
     *
     * @param str
     * @param type
     * @return
     */
    public static <T> T fromJson(String str, Class<T> type) {
        Gson gson = new Gson();
        return gson.fromJson(str, type);
    }

    public static <T> T fromJson(Object o, Class<T> type) {
        return fromJson(toJson(o),type);
    }

    public static List<?> array2Entities(Class<?> array, List type){
        List<Object> list = new ArrayList<>();
        for(Object o : type){
            list.add(fromJson(o,array));
        }
        return list;
    }
}