package com.cdbhe.plana.mvvm.utils.view;

import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ListView;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.utils.adapter.UtilsAdapter;
import com.cdbhe.plana.mvvm.utils.listener.UtilsButtonClickListener;
import com.cdbhe.plana.mvvm.utils.model.UtilsModel;
import com.cdbhe.plib.router.PRouter;
import com.cdbhe.plib.utils.ActivityStack;
import com.cdbhe.plib.utils.AlertUtils;
import com.cdbhe.plib.utils.DateUtils;
import com.cdbhe.plib.utils.LogUtils;
import com.cdbhe.plib.utils.SPUtils;
import com.cdbhe.plib.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UtilsActivity extends MyBaseActivity implements UtilsButtonClickListener {

    @BindView(R.id.listView)
    ListView listView;
    private List<UtilsModel> utilsModelList;
    private UtilsAdapter adapter;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_utils;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("工具类");
        initListView();
    }

    /**
     * 初始化ListView
     */
    private void initListView() {
        utilsModelList = new ArrayList<>();
        utilsModelList.add(new UtilsModel("Activity堆栈（入栈）", "ActivityStack.getInstance().pushActivity(activity);"));
        utilsModelList.add(new UtilsModel("Activity堆栈（弹栈）", "ActivityStack.getInstance().popActivity(\"activityName\")\nActivityStack.getInstance().popActivity(targetActivity)"));
        utilsModelList.add(new UtilsModel("Activity堆栈（清栈）", "ActivityStack.getInstance().clearAllActivity();"));
        utilsModelList.add(new UtilsModel("ImageHelper（图片助手工具）", "//压缩图片，返回Bitmap\nImageHelper.CompressionImage(filePath)"));
        utilsModelList.add(new UtilsModel("LogUtils（用于Log日志打印）", "debug:LogUtils.d(tag,msg) LogUtils.d(msg)默认debug tag\ninfo:LogUtils.i(tag,msg) LogUtils.i(msg)默认info tag\nerror:LogUtils.e(tag,msg) LogUtils.e(msg)默认error tag"));
        utilsModelList.add(new UtilsModel("MeasureUnitTranUtil（度量单位转换工具）", "//dp转px\nMeasureUnitTranUtil.dip2px(context,dpValue)\n//px转dp\nMeasureUnitTranUtil.px2dip(context,pxValue)"));
        utilsModelList.add(new UtilsModel("ToastUtils（吐司工具）", "//短时间显示吐司\nToastUtils.showShort(context,stringId)\nToastUtils.showShort(context,msgStr)\n//长时间显示吐司\nToastUtils.showLong(context,stringId)\nToastUtils.showLong(context,msgStr)\n//自定义显示时长\nToastUtils.show(context,stringId,during)\nToastUtils.show(context,msgStr,during)"));
        utilsModelList.add(new UtilsModel("AlertUtils（7style的AlertDialog）", "//参数分别为:上下文、标题、内容、确定点击回调事件\nAlertUtils.showAlert(context, title, content, confirmOnClickListener)"));
        utilsModelList.add(new UtilsModel("DateUtils（日期工具）", "//获取日期  2018-07-01 12:02:12\nDateUtils.getDateStr()\n//获取当前时间戳\nDateUtils.getTimeInMillis()\n//获取当前年份\nDateUtils.getCurrentYear()\n//获取当前月份\nDateUtils.getCurrentMonth()\n//获取当前日期号数\nDateUtils.getCurrentDate()\n//获取当前星期几\nDateUtils.getCurrentDay()"));
        utilsModelList.add(new UtilsModel("SPUtils（SharedPreferences存储工具类）", "// 默认创建的sp名称的文件\n" + "SPUtils.setParam(context,key,value)// 键值对方式存储数据\nSPUtils.getParam(context,key,defaultValue)// 获取key对应的数据\nSPUtils.clear(context)// 清除存储的数据"));
        adapter = new UtilsAdapter(this, utilsModelList, R.layout.adapter_utils_item, this);
        listView.setAdapter(adapter);
    }

    @Override
    public void onItemButtonClick(int position) {
        switch (position) {
            case 0:// Activity堆栈（入栈）
                ToastUtils.showLong(this,"当前Activity已在BaseActivity中入栈，勿需重复操作！");
                break;
            case 1:// Activity堆栈（弹栈）
                AlertUtils.showAlert(this, "提示", "是否将当前Activity进行弹栈操作？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        ActivityStack.getInstance().popActivity("UtilsActivity");
                        ActivityStack.getInstance().popActivity(UtilsActivity.this);
                    }
                });
                break;
            case 2:// Activity堆栈（清栈）
                AlertUtils.showAlert(this, "提示", "是否清空Activity堆栈？", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ActivityStack.getInstance().clearAllActivity();
                    }
                });
                break;
            case 3:// ImageHelper（图片助手工具）
                ToastUtils.showShort(this,"这个不好测试！");
                break;
            case 4:// LogUtils（用于Log日志打印）
                LogUtils.d("-->debug");
                LogUtils.d("TAG","-->debug");
                LogUtils.i("-->info");
                LogUtils.i("TAG","-->info");
                LogUtils.e("-->error");
                LogUtils.e("TAG","-->error");
                ToastUtils.showShort(this,"控制台已打印");
                break;
            case 5:// MeasureUnitTranUtil（度量单位转换工具）
                PRouter.getInstance().navigation(this,MeasureUnitTranUtilActivity.class);
                break;
            case 6:// ToastUtils（吐司工具）
//                ToastUtils.showShort(this,"showShort");
//                ToastUtils.showShort(this,R.string.app_name);
//                ToastUtils.showLong(this,"showLong");
//                ToastUtils.showLong(this,R.string.app_name);
//                ToastUtils.show(this,"show",2000);
                ToastUtils.show(this,R.string.app_name,2000);
                break;
            case 7:// AlertUtils（7style的AlertDialog）
                AlertUtils.showAlert(this, "AlertUtils提示标题", "这个是v7Style的AlertDialog", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ToastUtils.showShort(UtilsActivity.this,"点击了确定");
                    }
                });
                break;
            case 8:// DateUtils（日期工具）
                ToastUtils.showShort(this,"当前日期："+DateUtils.getDateStr());
//                ToastUtils.showShort(this,"当前时间戳："+DateUtils.getTimeInMillis());
//                ToastUtils.showShort(this,"当前年份："+DateUtils.getCurrentYear());
//                ToastUtils.showShort(this,"当前月份："+DateUtils.getCurrentMonth());
//                ToastUtils.showShort(this,"当前号数："+DateUtils.getCurrentDate());
//                ToastUtils.showShort(this,"当前周几："+DateUtils.getCurrentDate());
                break;
            case 9:// SPUtils（SharedPreferences存储工具类）
                SPUtils.setParam(this,"currentDate",DateUtils.getDateStr());
                ToastUtils.showLong(this,"SPUtils储存当期日期值："+SPUtils.getParam(this,"currentDate",""));
                break;
            default:
                break;
        }
    }
}
