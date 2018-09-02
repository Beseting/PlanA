package com.cdbhe.plana.mvvm.utils.adapter;

import android.content.Context;
import android.view.View;

import com.cdbhe.plana.R;
import com.cdbhe.plana.mvvm.utils.listener.UtilsButtonClickListener;
import com.cdbhe.plana.mvvm.utils.model.UtilsModel;
import com.cdbhe.plib.list.adapter.CommonAdapter;
import com.cdbhe.plib.list.convert.Converter;

import java.util.List;

public class UtilsAdapter extends CommonAdapter<UtilsModel> {
    private UtilsButtonClickListener utilsButtonClickListener;
    /**
     * 构造函数 传值初始化
     *
     * @param mContext
     * @param list
     * @param resLayoutId
     */
    public UtilsAdapter(Context mContext, List<UtilsModel> list, int resLayoutId,UtilsButtonClickListener utilsButtonClickListener) {
        super(mContext, list, resLayoutId);
        this.utilsButtonClickListener = utilsButtonClickListener;
    }

    @Override
    public void convert(Converter holder, UtilsModel item, final int position) {
        holder.setText(R.id.titleTv,item.getTitle());
        holder.setText(R.id.desc,item.getDesc());
        holder.setOnClickListener(R.id.testBtn, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                utilsButtonClickListener.onItemButtonClick(position);
            }
        });
    }
}
