package com.cdbhe.plib.utils;

import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * Created by Kevin on 2018/3/8.
 */

public class CrashReportHelper {
    public static void initCrashReport(Context context,String appId,boolean isDebug){
        // 初始化Bugly
        String processName = AppUtils.getProcessName(android.os.Process.myPid());// 获取当前进程名
        CrashReport.UserStrategy strategy = new CrashReport.UserStrategy(context);// 设置是否为上报进程
        strategy.setUploadProcess(processName == null || processName.equals(context.getPackageName()));
        CrashReport.initCrashReport(context, appId, isDebug, strategy);
    }
}