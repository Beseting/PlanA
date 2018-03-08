package com.cdbhe.plib.utils;

import android.app.Activity;

import java.util.Stack;

/**
 * Activity堆栈
 * Created by Kevin on 2018/1/30.
 */
public class ActivityStack {
	private static Stack<Activity> mActivityStack = new Stack<>();
    private static ActivityStack instance = new ActivityStack();

    public static ActivityStack getInstance() {
        return instance;
    }

    /**
     * 弹出Activity并销毁
     * @param targetActivity
     */
    public void popActivity(Activity targetActivity) {
        if (targetActivity != null) {
            targetActivity.finish();
            mActivityStack.remove(targetActivity);
            targetActivity = null;
        }
    }

    /**
     * 弹出Activity并销毁
     * @param activityName
     * @return
     */
    public boolean popActivity(String activityName){
        for(Activity activity:mActivityStack){
            if(activity.getLocalClassName().equals(activityName)){
                popActivity(activity);
                return true;
            }
        }
        return false;
    }

    /**
     * Activity入栈
     * @param activity
     */
    public void pushActivity(Activity activity) {
        mActivityStack.add(activity);
    }

    /**
     * 清Activity栈
     */
    public void clearAllActivity() {
        while (!mActivityStack.isEmpty()) {
            Activity activity = mActivityStack.pop();
            if (activity != null) {
                activity.finish();
            }
        }
    }
}