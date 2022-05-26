package com.ym.plib.utils;

import android.util.Log;
import com.ym.plib.BuildConfig;


/**
 * Created by Jabez on 2017/5/16.
 */

public class LogUtils {
    public static String LOG_INFO = "info";
    public static String LOG_DEBUG = "debug";
    public static String LOG_ERROR = "error";

    public static boolean isShowLog = BuildConfig.DEBUG;

    public static void i(String msg){
        if(isShowLog){
            Log.i(LOG_INFO,msg);
        }
    }
    public static void i(String TAG, String msg){
        if(isShowLog){
            Log.i(TAG,msg);
        }
    }

    public static void d(String msg){
        if(isShowLog){
            Log.d(LOG_DEBUG,msg);
        }
    }
    public static void d(String TAG, String msg){
        if(isShowLog){
            Log.d(TAG,msg);
        }
    }

    public static void e(String msg){
        if(isShowLog){
            Log.e(LOG_ERROR,msg);
        }
    }
    public static void e(String TAG, String msg){
        if(isShowLog){
            Log.e(TAG,msg);
        }
    }
}
