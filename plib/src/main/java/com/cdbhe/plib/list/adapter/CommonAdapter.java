package com.cdbhe.plib.list.adapter;

import android.content.Context;
import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.cdbhe.plib.list.convert.Converter;

import java.util.ArrayList;
import java.util.List;

public abstract class CommonAdapter<T> extends BaseAdapter {
    private Context mContext;
    private List<T> list;
    private int resLayoutId;

    /**
     * 构造函数 传值初始化
     * @param mContext
     * @param list
     * @param resLayoutId
     */
    public CommonAdapter(Context mContext, List<T> list, int resLayoutId) {
        this.mContext = mContext;
        this.list = list;
        this.resLayoutId = resLayoutId;
    }

    public Context getContext() {
        return mContext;
    }

    /**
     * 固定位置添加元素
     * @param position
     * @param data
     */
    public void addData(@IntRange(from = 0) int position, @NonNull T data) {
        list.add(position, data);
        notifyDataSetChanged();
    }

    /**
     * 添加元素
     * @param data
     */
    public void addData(@NonNull T data) {
        list.add(data);
        notifyDataSetChanged();
    }

    /**
     * 删除固定位置元素
     * @param position
     */
    public void remove(@IntRange(from = 0) int position){
        list.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 删除元素
     * @param data
     */
    public void remove(@NonNull T data){
        list.remove(data);
        notifyDataSetChanged();
    }

    /**
     * 设置新元素
     * @param index
     * @param data
     */
    public void setData(int index,@NonNull T data){
        list.set(index, data);
        notifyDataSetChanged();
    }

    /**
     * 设置新列表
     * @param data
     */
    public void setNewData(@NonNull List<T> data) {
        this.list = data == null ? new ArrayList<T>() : data;
        notifyDataSetChanged();
    }

    /**
     * 获取数据列表
     * @return
     */
    @NonNull
    public List<T> getData() {
        return list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(resLayoutId,parent, false);
        }
        Converter converter = Converter.get(convertView);
        convert(converter, list.get(position),position);
        return convertView;
    }

    /**
     * 抽象方法 自定义数据处理
     * @param holder
     * @param item
     * @param position
     */
    public abstract void convert(Converter holder, T item,int position);
}