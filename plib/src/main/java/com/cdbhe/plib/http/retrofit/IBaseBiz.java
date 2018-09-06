package com.cdbhe.plib.http.retrofit;

import android.app.Activity;
import android.content.Context;

/**
 * Created by Administrator on 2018/3/6.
 */

public interface IBaseBiz {
    Activity getActivity();
    void toActivity(Object... objects);
}
