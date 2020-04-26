package com.cdbhe.plana.main;

import android.app.Application;

import com.cdbhe.plib.http.retrofit.HttpConfig;
import com.cdbhe.plib.http.retrofit.RetrofitClient;

/**
 * Created by Kevin on 2018/3/5.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化Retrofit
        RetrofitClient.getInstance().init(new HttpConfig.Builder().setBaseUrl("https://restapi.amap.com/").build());
    }
}
