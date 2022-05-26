package com.ym.plana.mvvm.common_adapter.view;

import android.content.Context;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.ListView;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plana.mvvm.common_adapter.biz.ICommonAdapterBiz;
import com.ym.plana.mvvm.common_adapter.vm.CommonAdapterVm;

import butterknife.BindView;

public class CommonAdapterActivity extends MyBaseActivity implements ICommonAdapterBiz {

    @BindView(R.id.gridView)
    GridView gridView;
    @BindView(R.id.listView)
    ListView listView;
    private CommonAdapterVm vm;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_common_adapter;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("万能适配器");
        vm = new CommonAdapterVm(this);
        vm.init();
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public GridView getGridView() {
        return gridView;
    }

    @Override
    public ListView getListView() {
        return listView;
    }
}
