package com.cdbhe.plib.router;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.core.app.ActivityCompat;
import androidx.core.app.ActivityOptionsCompat;

import com.cdbhe.plib.R;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Kevin on 2018/1/30.
 */

public class PRouter {
    private static PRouter pRouter;
    private static Bundle bundle;
    public static PRouter getInstance(){
        return pRouter = new PRouter();
    }

    private PRouter(){
        bundle = new Bundle();
    }

    public void navigation(Context context,Class<?> targetActivity){
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(context, R.anim.anim_activity_in, R.anim.anim_activity_out);
//            ActivityCompat.startActivity(context, new Intent(context, targetActivity), compat.toBundle());
//        }else{
//            context.startActivity(new Intent(context,targetActivity));
//        }
        context.startActivity(new Intent(context, targetActivity));
    }

    public void navigation(Activity activity, Class<?> targetActivity,boolean isFinish){
        navigation(activity,targetActivity);
        if(isFinish) activity.finish();
    }

    public void navigation(Activity activity,Class<?> targetActivity,int requestCode){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptionsCompat compat = ActivityOptionsCompat.makeCustomAnimation(activity, R.anim.anim_activity_in, R.anim.anim_activity_out);
            ActivityCompat.startActivityForResult(activity, new Intent(activity, targetActivity), requestCode, compat.toBundle());
        }else{
            activity.startActivityForResult(new Intent(activity,targetActivity),requestCode);
        }
    }

    /**
     * 路由加int参
     * @param key
     * @param value
     */
    public PRouter withInt(String key,int value){
        bundle.putInt(key,value);
        return pRouter;
    }

    /**
     * 路由获取int值
     * @param key
     * @return
     */
    public static int getInt(String key){
        return bundle.getInt(key);
    }

    /**
     * 路由加long参
     * @param key
     * @param value
     */
    public PRouter withLong(String key,long value){
        bundle.putLong(key,value);
        return pRouter;
    }

    /**
     * 路由获取long值
     * @param key
     * @return
     */
    public static long getLong(String key){
        return bundle.getLong(key);
    }

    /**
     * 路由加Float参
     * @param key
     * @param value
     */
    public PRouter withFloat(String key,float value){
        bundle.putFloat(key,value);
        return pRouter;
    }

    /**
     * 路由获取float值
     * @param key
     * @return
     */
    public static float getFloat(String key){
        return bundle.getFloat(key);
    }

    /**
     * 路由加Double参
     * @param key
     * @param value
     */
    public PRouter withDouble(String key,double value){
        bundle.putDouble(key,value);
        return pRouter;
    }

    /**
     * 路由获取double值
     * @param key
     * @return
     */
    public static double getDouble(String key){
        return bundle.getDouble(key);
    }

    /**
     * 路由加String参
     * @param key
     * @param value
     */
    public PRouter withString(String key,String value){
        bundle.putString(key,value);
        return pRouter;
    }

    /**
     * 路由获取String值
     * @param key
     * @return
     */
    public static String getString(String key){
        return bundle.getString(key);
    }

    /**
     * 路由加Boolean参
     * @param key
     * @param value
     */
    public PRouter withBoolean(String key,boolean value){
        bundle.putBoolean(key,value);
        return pRouter;
    }

    /**
     * 路由获取boolean值
     * @param key
     * @return
     */
    public static boolean getBoolean(String key){
        return bundle.getBoolean(key);
    }

    /**
     * 路由加Serializable参
     * @param key
     * @param value
     */
    public PRouter withSerializable(String key,Serializable value){
        bundle.putSerializable(key,value);
        return pRouter;
    }

    /**
     * 路由获取Serializable值
     * @param key
     * @return
     */
    public static Serializable getSerializable(String key){
        return bundle.getSerializable(key);
    }

    /**
     * 路由加ArrayList<Integer>参
     * @param key
     * @param value
     */
    public PRouter withIntegerArrayList(String key,ArrayList<Integer> value){
        bundle.putIntegerArrayList(key,value);
        return pRouter;
    }

    /**
     * 路由获取ArrayList<Integer>值
     * @param key
     * @return
     */
    public static ArrayList<Integer> getIntegerArrayList(String key){
        return bundle.getIntegerArrayList(key);
    }

    /**
     * 路由加ArrayList<String>参
     * @param key
     * @param value
     */
    public PRouter withStringArrayList(String key,ArrayList<String> value){
        bundle.putStringArrayList(key,value);
        return pRouter;
    }

    /**
     * 路由获取ArrayList<String>值
     * @param key
     * @return
     */
    public static ArrayList<String> getStringArrayList(String key){
        return bundle.getStringArrayList(key);
    }

    /**
     * 路由获取Object值
     * @param key
     * @return
     */
    public static Object getObject(String key){
        return bundle.get(key);
    }

    /**
     * 路由加Bundle参
     * @param bundleParam
     * @return
     */
    public PRouter withBundle(Bundle bundleParam){
        bundle = bundleParam;
        return pRouter;
    }

    /**
     * 路由获取bundle值
     * @return
     */
    public static Bundle getBundle(){
        return bundle;
    }
}
