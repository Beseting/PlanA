package com.ym.plana.mvvm.base_activity.listener;

import android.view.View;

import com.ym.plana.mvvm.base_activity.model.ButtonModel;

public interface OnBaseActivityButtonClickListener {
    void onButtonClick(View view, ButtonModel buttonModel,int position);
}
