package com.cdbhe.plana;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cdbhe.plib.base.BaseActivity;
import com.cdbhe.plib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

public class MainActivity extends BaseActivity{
    @BindView(R.id.listView)ListView listView;
    @Override
    public int getContentViewResId() {
        return R.layout.activity_main;
    }

    @Override
    public void init(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        setTitle("CommonAdapter");
        initListView();
    }

    private void initListView(){
        List<MusicModel> musicModelList = new ArrayList<>();
        musicModelList.add(new MusicModel("http://tx.haiqq.com/uploads/allimg/170509/0R6452153-3.jpg","许嵩-摄影艺术","04:27"));
        musicModelList.add(new MusicModel("http://www.jqff.com.cn/Upload/FCKEditor/37-151202145G6-50.jpg","赵雷-成都","06:18"));
        musicModelList.add(new MusicModel("http://mp2.qiyipic.com/image/20180415/39/89/ppu_215302300102_pp_601_m1_300_300.jpg","冯提莫-再见前任","03:20"));
        musicModelList.add(new MusicModel("http://img4.cache.netease.com/cnews/2014/3/4/20140304180507d4eab.jpg","陈奕迅-约定","05:36"));
        MyAdapter myAdapter = new MyAdapter(this, musicModelList,R.layout.adapter_item);
        listView.setAdapter(myAdapter);
    }

    @OnItemClick(R.id.listView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id){
        ToastUtils.showShort(this,"位置："+position);
    }
}