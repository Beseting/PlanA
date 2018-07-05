package com.cdbhe.plib.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cdbhe.plib.R;
import com.wang.avi.AVLoadingIndicatorView;


/**
 * Created by Kevin on 2017/5/17.
 */

public class LoadingDialog {
    public Dialog dialog;
    public AVLoadingIndicatorView avView;

    public Dialog createLoadingDialog(Context context) {

        // 首先得到整个View
        View view = LayoutInflater.from(context).inflate(R.layout.layout_progress_dialog, null);
        // 获取整个布局
        RelativeLayout layout = view.findViewById(R.id.dialog_view);
        //获取加载动画
        avView = view.findViewById(R.id.av_loading_indicator_view);

        // 创建自定义样式的Dialog
        dialog = new Dialog(context, R.style.loading_dialog);
        // 设置返回键无效
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(layout, new RelativeLayout.LayoutParams(
                MeasureUnitTranUtil.dip2px(context,250),
                MeasureUnitTranUtil.dip2px(context,160)));

        return dialog;
    }
    public void showDialog(){
        dialog.show();
    }
    public void closeDialog(){
        dialog.dismiss();
    }
    public void setDialogIndicator(String indicatorName, String indicatorColor){
        avView.setIndicator(indicatorName);
        avView.setIndicatorColor(Color.parseColor(indicatorColor));
    }
}