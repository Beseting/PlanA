package com.ym.plana.mvvm.base_activity.view;

import android.Manifest;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ListView;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plana.mvvm.base_activity.adapter.BaseActivityButtonAdapter;
import com.ym.plana.mvvm.base_activity.listener.OnBaseActivityButtonClickListener;
import com.ym.plana.mvvm.base_activity.model.ButtonModel;
import com.ym.plib.base.BaseActivity;
import com.ym.plib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class BaseActivityDemo extends MyBaseActivity implements OnBaseActivityButtonClickListener,BaseActivity.OnTitleBarListener {

    @BindView(R.id.listView)
    ListView listView;
    private List<ButtonModel> buttonModelList;
    private BaseActivityButtonAdapter adapter;
    private boolean isFullScreen = false;//是否全屏
    private final int PERMISSION_REQUEST_CODE = 1000;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_base_demo;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("BaseActivity");//设置标题
        initListView();//初始化列表
    }

    /**
     * 初始化按钮列表
     */
    private void initListView() {
        buttonModelList = new ArrayList<>();
        buttonModelList.add(new ButtonModel("6.0+权限动态请求"));
        buttonModelList.add(new ButtonModel("是否全屏"));
        buttonModelList.add(new ButtonModel("状态栏透明"));
        buttonModelList.add(new ButtonModel("设置状态栏颜色（沉浸式）"));
        buttonModelList.add(new ButtonModel("开启菊花加载进度窗"));
        buttonModelList.add(new ButtonModel("隐藏标题栏"));
        buttonModelList.add(new ButtonModel("显示标题栏"));
        buttonModelList.add(new ButtonModel("隐藏返回icon"));
        buttonModelList.add(new ButtonModel("显示返回icon"));
        buttonModelList.add(new ButtonModel("设置标题"));
        buttonModelList.add(new ButtonModel("标题栏显示更多icon"));
        buttonModelList.add(new ButtonModel("替换标题栏更多icon"));
        buttonModelList.add(new ButtonModel("设置标题栏背景颜色"));
        buttonModelList.add(new ButtonModel("设置返回icon"));
        buttonModelList.add(new ButtonModel("设置标题颜色"));
        buttonModelList.add(new ButtonModel("设置标题字体大小"));

        adapter = new BaseActivityButtonAdapter(this, buttonModelList, R.layout.adapter_base_activity_button_item, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onButtonClick(View view, ButtonModel buttonModel, int position) {
        switch (position) {
            case 0://6.0+权限动态请求
                requestPermission(new String[]{Manifest.permission.CAMERA},PERMISSION_REQUEST_CODE);
                break;
            case 1://是否显示状态栏
                setIsFullScreen(isFullScreen = !isFullScreen);
                break;
            case 2://状态栏透明
                setStatusBarTransparent();
                break;
            case 3://设置状态栏颜色（沉浸式）
                setStatusBarColor(Color.parseColor("#445b53"));
                break;
            case 4://点击 开启菊花加载弹窗 showDialog()
                showDialog();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        closeDialog();
                        ToastUtils.showShort(BaseActivityDemo.this,"调用closeDialog()关闭");
                    }
                },1500);
                break;
            case 5://隐藏标题栏
                hideTitleBar();
                break;
            case 6://显示标题栏
                showTitleBar();
                break;
            case 7://隐藏返回icon
                hideEsc();
                break;
            case 8://显示返回icon
                showEsc();
                break;
            case 9://设置标题
                setTitle("新标题");
                break;
            case 10://标题栏显示更多icon
                showMore(this);
                break;
            case 11://替换标题栏更多icon
                replaceMoreIcon(R.mipmap.ic_more);
                break;
            case 12://设置标题栏背景颜色
                setTitleBarBg(Color.RED);
                break;
            case 13://设置返回icon
                setEscIcon(R.mipmap.ic_esc);
                break;
            case 14://设置标题颜色
                setTitleTextColor(Color.BLACK);
                break;
            case 15://设置标题字体大小
                setTitleTextSize(18);
                break;
            default:
                break;
        }
    }

    @Override
    public void clickMore(View view) {
        ToastUtils.showShort(this,"点击了更多icon");
    }

    @Override
    public void permissionSucceed(int code) {
        super.permissionSucceed(code);
        ToastUtils.showShort(this,"权限申请成功");
    }

    @Override
    public void permissionFailing(int code) {
        super.permissionFailing(code);
        ToastUtils.showShort(this,"权限申请失败");
    }
}
