package com.cdbhe.plana.mvvm.widget.view;

import android.content.Context;
import android.os.Bundle;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.widget.biz.IWidgetBiz;
import com.cdbhe.plana.mvvm.widget.vm.WidgetVm;
import com.cdbhe.plib.widget.NoScrollGridView;
import com.cdbhe.plib.widget.NoScrollListView;
import com.cdbhe.plib.widget.RatingBar;

import butterknife.BindView;

public class WidgetActivity extends MyBaseActivity implements IWidgetBiz {

    @BindView(R.id.noScrollGridView)
    NoScrollGridView noScrollGridView;
    @BindView(R.id.noScrollListView)
    NoScrollListView noScrollListView;
    @BindView(R.id.ratingBar)
    RatingBar ratingBar;
    private WidgetVm vm;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_widget;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("自定义控件");
        vm = new WidgetVm(this);
        vm.init();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public NoScrollGridView getNoScrollGridView() {
        return noScrollGridView;
    }

    @Override
    public NoScrollListView getNoScrollListView() {
        return noScrollListView;
    }

    @Override
    public RatingBar getRatingBar() {
        return ratingBar;
    }
}
