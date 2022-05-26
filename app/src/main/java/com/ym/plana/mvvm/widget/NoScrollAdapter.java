package com.ym.plana.mvvm.widget;

import android.content.Context;

import com.ym.plana.R;
import com.ym.plana.mvvm.widget.model.NoScrollModel;
import com.ym.plib.list.adapter.CommonAdapter;
import com.ym.plib.list.convert.Converter;

import java.util.List;

public class NoScrollAdapter extends CommonAdapter<NoScrollModel> {
    /**
     * 构造函数 传值初始化
     *
     * @param mContext
     * @param list
     * @param resLayoutId
     */
    public NoScrollAdapter(Context mContext, List<NoScrollModel> list, int resLayoutId) {
        super(mContext, list, resLayoutId);
    }

    @Override
    public void convert(Converter holder, NoScrollModel item, int position) {
        holder.setText(R.id.tv,item.getStr());
    }
}
