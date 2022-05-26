package com.ym.plana.mvvm.base_activity.adapter;

import android.content.Context;
import android.view.View;

import com.ym.plana.R;
import com.ym.plana.mvvm.base_activity.listener.OnBaseActivityButtonClickListener;
import com.ym.plana.mvvm.base_activity.model.ButtonModel;
import com.ym.plib.list.adapter.CommonAdapter;
import com.ym.plib.list.convert.Converter;

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
