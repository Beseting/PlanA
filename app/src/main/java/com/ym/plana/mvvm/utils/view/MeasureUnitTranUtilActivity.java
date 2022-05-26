package com.ym.plana.mvvm.utils.view;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.ym.plana.R;
import com.ym.plana.base.MyBaseActivity;
import com.ym.plib.utils.MeasureUnitTranUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class MeasureUnitTranUtilActivity extends MyBaseActivity {

    @BindView(R.id.topDpEt)
    EditText topDpEt;
    @BindView(R.id.topPxEt)
    EditText topPxEt;
    @BindView(R.id.bottomPxEt)
    EditText bottomPxEt;
    @BindView(R.id.bottomDpEt)
    EditText bottomDpEt;

    @Override
    public int getLayoutResId() {
        return R.layout.activity_measure_unit_tran_util;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("dp px单位转换");
    }

    @OnClick(R.id.btn)
    public void click(View view){
        int dpValue;
        int pxValue;
        if(TextUtils.isEmpty(topDpEt.getText().toString())){
            dpValue = 0;
        }else{
            dpValue = Integer.parseInt(topDpEt.getText().toString());
        }
        if(TextUtils.isEmpty(bottomPxEt.getText().toString())){
            pxValue = 0;
        }else{
            pxValue = Integer.parseInt(bottomPxEt.getText().toString());
        }
        topPxEt.setText(String.valueOf(MeasureUnitTranUtil.dip2px(this,dpValue)));
        bottomDpEt.setText(String.valueOf(MeasureUnitTranUtil.px2dip(this,pxValue)));
    }
}
