package com.ym.plana.base;

import android.app.Activity;

/**
 * Created by Administrator on 2018/3/6.
 */

public interface IBaseBiz {
    Activity getActivity();
    void toActivity(Object... objects);
}
