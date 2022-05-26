package com.ym.plana.mvvm.widget.vm;

import com.ym.plana.R;
import com.ym.plana.mvvm.widget.NoScrollAdapter;
import com.ym.plana.mvvm.widget.biz.IWidgetBiz;
import com.ym.plana.mvvm.widget.model.NoScrollModel;
import com.ym.plib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class WidgetVm {
    private IWidgetBiz iWidgetBiz;
    private List<NoScrollModel> noScrollModelList;
    private NoScrollAdapter noScrollGridAdapter;
    private NoScrollAdapter noScrollListAdapter;

    public WidgetVm(IWidgetBiz iWidgetBiz) {
        this.iWidgetBiz = iWidgetBiz;
    }

    public void init(){
        noScrollModelList = new ArrayList<>();
        noScrollModelList.add(new NoScrollModel("1.刘德华"));
        noScrollModelList.add(new NoScrollModel("2.张学友"));
        noScrollModelList.add(new NoScrollModel("3.郭富城"));
        noScrollModelList.add(new NoScrollModel("4.黎明"));
        noScrollGridAdapter = new NoScrollAdapter(iWidgetBiz.getContext(),noScrollModelList,R.layout.adapter_grid_item);
        iWidgetBiz.getNoScrollGridView().setAdapter(noScrollGridAdapter);
        noScrollListAdapter = new NoScrollAdapter(iWidgetBiz.getContext(),noScrollModelList,R.layout.adapter_list_item);
        iWidgetBiz.getNoScrollListView().setAdapter(noScrollListAdapter);

        iWidgetBiz.getRatingBar().setOnRatingChangeListener(ratingCount -> {
            ToastUtils.showShort(iWidgetBiz.getContext(),"-->"+ratingCount);
        });
    }
}
