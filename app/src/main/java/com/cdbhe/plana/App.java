package com.cdbhe.plana;

import android.app.Application;

import com.cdbhe.plib.http.retrofit.RetrofitClient;
import com.cdbhe.plib.utils.CrashReportHelper;

/**
 * Created by Kevin on 2018/3/5.
 */

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        // 初始化Bugly
        CrashReportHelper.initCrashReport(getApplicationContext(),"420f93d2c2",true);

        //初始化Retrofit
        RetrofitClient.initBaseUrl("http://118.190.149.80:8090/");
    }
}
