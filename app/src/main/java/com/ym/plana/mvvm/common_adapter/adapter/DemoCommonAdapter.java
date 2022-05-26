package com.ym.plana.mvvm.common_adapter.adapter;

import android.content.Context;
import android.widget.ImageView;

import com.ym.plana.R;
import com.ym.plana.mvvm.common_adapter.model.CommonAdapterModel;
import com.ym.plib.list.adapter.CommonAdapter;
import com.ym.plib.list.convert.Converter;
import com.squareup.picasso.Picasso;

import java.util.List;

public class DemoCommonAdapter extends CommonAdapter<CommonAdapterModel> {

    /**
     * 构造函数 传值初始化
     *
     * @param mContext
     * @param list
     * @param resLayoutId
     */
    public DemoCommonAdapter(Context mContext, List<CommonAdapterModel> list, int resLayoutId) {
        super(mContext, list, resLayoutId);
    }

    @Override
    public void convert(Converter holder, CommonAdapterModel item, int position) {
        Picasso.get().load(item.getImg()).into((ImageView) holder.getView(R.id.imageIv));
        holder.setText(R.id.nameTv, item.getName());
    }
}
