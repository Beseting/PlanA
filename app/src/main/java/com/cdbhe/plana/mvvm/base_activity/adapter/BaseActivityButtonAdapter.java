package com.cdbhe.plana.mvvm.base_activity.adapter;

import android.content.Context;
import android.view.View;

import com.cdbhe.plana.R;
import com.cdbhe.plana.mvvm.base_activity.listener.OnBaseActivityButtonClickListener;
import com.cdbhe.plana.mvvm.base_activity.model.ButtonModel;
import com.cdbhe.plib.list.adapter.CommonAdapter;
import com.cdbhe.plib.list.convert.Converter;
import com.cdbhe.plib.utils.ToastUtils;

import java.util.List;

public class BaseActivityButtonAdapter extends CommonAdapter<ButtonModel> {
    private OnBaseActivityButtonClickListener onBaseActivityButtonClickListener;
    public BaseActivityButtonAdapter(Context mContext, List<ButtonModel> list, int resLayoutId) {
        super(mContext, list, resLayoutId);
    }

    public BaseActivityButtonAdapter(Context mContext, List<ButtonModel> list, int resLayoutId,OnBaseActivityButtonClickListener onBaseActivityButtonClickListener) {
        super(mContext, list, resLayoutId);
        this.onBaseActivityButtonClickListener = onBaseActivityButtonClickListener;
    }

    @Override
    public void convert(Converter holder, final ButtonModel item, final int position) {
        holder.setText(R.id.btn,item.getBtnText());
        // 设置点击事件
        holder.setOnClickListener(R.id.btn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBaseActivityButtonClickListener.onButtonClick(v,item,position);
            }
        });
    }
}
