package com.ym.plana.main;

import android.app.Application;

import com.ym.plib.http.retrofit.HttpConfig;
import com.ym.plib.http.retrofit.RetrofitClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Kevin on 2018/3/5.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Retrofit
        Map<String, String> commonHeaderMap = new HashMap<>();
        commonHeaderMap.put("token11", "111");
        RetrofitClient.getInstance().init(new HttpConfig.Builder().setBaseUrl("https://restapi.amap.com/").build(), commonHeaderMap);
    }
}
