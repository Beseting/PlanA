package com.ym.plib.utils;

import java.util.Calendar;

/**
 * Created by Kevin on 2018/2/25.
 */

public class DateUtils {
    /**
     * 获取日期  2016-10-01 12:02:12
     * @return
     */
    public static String getDateStr(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        int second = calendar.get(Calendar.SECOND);

        String monthStr = month + "";
        if(month<10){
            monthStr = "0" + month;
        }
        String dayStr = day + "";
        if(day<10){
            dayStr = "0" + day;
        }
        String hourStr = hour + "";
        if(hour<10){
            hourStr = "0" + hour;
        }
        String minuteStr = minute + "";
        if(minute<10){
            minuteStr = "0" + minute;
        }
        String secondStr = second + "";
        if(second<10){
            secondStr = "0" + second;
        }
        return year + "-" + monthStr + "-" + dayStr + " " + hourStr + ":" +	minuteStr + ":" + secondStr;
    }

    /**
     * 获取当前时间戳
     * @return
     */
    public static long getTimeInMillis(){
        return Calendar.getInstance().getTimeInMillis();
    }

    /**
     * 获取当前年份
     * @return
     */
    public static int getCurrentYear(){
        return Calendar.getInstance().get(Calendar.YEAR);
    }

    /**
     * 获取当前月份
     * @return
     */
    public static int getCurrentMonth(){
        return Calendar.getInstance().get(Calendar.MONTH) + 1;
    }

    /**
     * 获取当前日期号数
     * @return
     */
    public static int getCurrentDate(){
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前星期几
     * @return
     */
    public static int getCurrentDay(){
        return Calendar.getInstance().get(Calendar.DAY_OF_WEEK) - 1;
    }
}
