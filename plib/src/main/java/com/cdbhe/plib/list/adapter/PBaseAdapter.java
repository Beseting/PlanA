package com.cdbhe.plib.list.adapter;

import android.view.View;
import android.view.ViewGroup;

public abstract class PBaseAdapter extends android.widget.BaseAdapter {

    public abstract int getLayoutResId();
    public abstract void bindData();
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }
}
