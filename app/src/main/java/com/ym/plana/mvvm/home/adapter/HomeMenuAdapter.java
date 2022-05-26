package com.ym.plana.mvvm.home.adapter;

import android.content.Context;

import com.ym.plana.R;
import com.ym.plana.mvvm.home.model.HomeMenuModel;
import com.ym.plib.list.adapter.CommonAdapter;
import com.ym.plib.list.convert.Converter;

import java.util.List;

public class HomeMenuAdapter extends CommonAdapter<HomeMenuModel> {
    public HomeMenuAdapter(Context mContext, List<HomeMenuModel> list, int resLayoutId) {
        super(mContext, list, resLayoutId);
    }

    @Override
    public void convert(Converter holder, HomeMenuModel item, int position) {
        holder.setImageResource(R.id.menu_icon,item.getIcon());
        holder.setText(R.id.menu_name,item.getName());
    }
}
