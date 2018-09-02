package com.cdbhe.plana;

import android.content.Context;
import android.view.View;

import com.cdbhe.plib.list.adapter.CommonAdapter;
import com.cdbhe.plib.list.convert.Converter;
import com.cdbhe.plib.utils.ToastUtils;

import java.util.List;

public class MyAdapter extends CommonAdapter<MusicModel> {

    public MyAdapter(Context mContext, List<MusicModel> list, int resLayoutId) {
        super(mContext, list, resLayoutId);
    }

    @Override
    public void convert(Converter holder, final MusicModel item,final int position) {
        holder.setImageResource(R.id.iv,item.getImage());
        holder.setText(R.id.titleTv,item.getTitle());
        holder.setText(R.id.timeTv,item.getTime());
        holder.setOnClickListener(R.id.icDel, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showShort(getContext(),"位置："+position);
            }
        });
    }
}