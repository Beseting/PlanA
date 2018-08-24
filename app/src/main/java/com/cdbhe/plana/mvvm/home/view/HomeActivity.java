package com.cdbhe.plana.mvvm.home.view;

import android.os.Bundle;
import android.widget.GridView;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.home.adapter.HomeMenuAdapter;
import com.cdbhe.plana.mvvm.home.model.HomeMenuModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeActivity extends MyBaseActivity{
    @BindView(R.id.gridView)GridView gridView;
    private List<HomeMenuModel> homeMenuModelList;
    private HomeMenuAdapter homeMenuAdapter;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_home;
    }

    @Override
    public void initView(Bundle var1) {
        setIsShowStatusBar(false);//隐藏状态栏
        hideTitleBar();//隐藏标题栏
        initMenuGridView();
    }

    /**
     * 初始化菜单
     */
    private void initMenuGridView(){
        homeMenuModelList = new ArrayList<>();
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_activity,"BaseActivity"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_router,"PRouter路由"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_activity,"BaseActivity"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_activity,"BaseActivity"));
        homeMenuAdapter = new HomeMenuAdapter(this,homeMenuModelList,R.layout.adapter_home_menu);
        gridView.setAdapter(homeMenuAdapter);
    }
}