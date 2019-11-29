package com.cdbhe.plana.mvvm.widget.biz;

import android.content.Context;

import com.cdbhe.plib.http.retrofit.IBaseBiz;
import com.cdbhe.plib.widget.NoScrollGridView;
import com.cdbhe.plib.widget.NoScrollListView;
import com.cdbhe.plib.widget.RatingBar;

public interface IWidgetBiz {
    Context getContext();
    NoScrollGridView getNoScrollGridView();
    NoScrollListView getNoScrollListView();
    RatingBar getRatingBar();
}
