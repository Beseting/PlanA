package com.ym.plana.mvvm.widget.biz;

import android.content.Context;

import com.ym.plib.widget.NoScrollGridView;
import com.ym.plib.widget.NoScrollListView;
import com.ym.plib.widget.RatingBar;

public interface IWidgetBiz {
    Context getContext();
    NoScrollGridView getNoScrollGridView();
    NoScrollListView getNoScrollListView();
    RatingBar getRatingBar();
}
