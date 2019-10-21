package com.cdbhe.plana.mvvm.home.view;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.base_activity.view.BaseActivityDemo;
import com.cdbhe.plana.mvvm.home.adapter.HomeMenuAdapter;
import com.cdbhe.plana.mvvm.home.model.HomeMenuModel;
import com.cdbhe.plana.mvvm.http.view.HttpDemoActivity;
import com.cdbhe.plana.mvvm.router_manage.view.RouterManageActivity;
import com.cdbhe.plana.mvvm.upload_download.view.UploadDownloadActivity;
import com.cdbhe.plana.mvvm.utils.view.UtilsActivity;
import com.cdbhe.plib.router.PRouter;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnItemClick;

public class HomeActivity extends MyBaseActivity {
    @BindView(R.id.banner)
    ImageView banner;
    @BindView(R.id.gridView)
    GridView gridView;
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
    private void initMenuGridView() {
        homeMenuModelList = new ArrayList<>();
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_activity, "BaseActivity"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_router, "路由管理"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_utils, "工具类"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_http, "网络请求"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_upload_download, "上传下载"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_custom, "自定义控件"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_photo_browse, "图片浏览"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_adapter, "万能适配器"));
        homeMenuModelList.add(new HomeMenuModel(R.mipmap.ic_home_menu_more, "更多"));
        homeMenuAdapter = new HomeMenuAdapter(this, homeMenuModelList, R.layout.adapter_home_menu);
        gridView.setAdapter(homeMenuAdapter);
    }

    @OnItemClick(R.id.gridView)
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0://BaseActivity
                PRouter.getInstance().navigation(this, BaseActivityDemo.class);
                break;
            case 1://路由管理
                PRouter.getInstance().navigation(this, RouterManageActivity.class);
                break;
            case 2://工具类
                PRouter.getInstance().navigation(this, UtilsActivity.class);
                break;
            case 3://网络请求
                PRouter.getInstance().navigation(this, HttpDemoActivity.class);
                break;
            case 4://上传下载
                PRouter.getInstance().navigation(this, UploadDownloadActivity.class);
                break;
            case 5://自定义控件

                break;
            case 6://图片浏览

                break;
            case 7://万能适配器

                break;
            default://其他
                Snackbar.make(gridView,"持续维护中，敬请期待！", Snackbar.LENGTH_SHORT).show();
                break;
        }
    }
}