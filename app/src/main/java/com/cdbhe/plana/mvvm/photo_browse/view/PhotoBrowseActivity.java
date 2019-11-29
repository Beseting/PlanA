package com.cdbhe.plana.mvvm.photo_browse.view;

import android.os.Bundle;
import android.view.View;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.kevin.photo_browse.ImageBrowseIntent;

import java.util.ArrayList;

import butterknife.OnClick;

public class PhotoBrowseActivity extends MyBaseActivity {

    @Override
    public int getLayoutResId() {
        return R.layout.activity_photo_browse;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("图片浏览");
    }

    @OnClick({R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn1:
                ImageBrowseIntent.showResIdImageBrowse(this,R.mipmap.banner);
                break;
            case R.id.btn2:
                ArrayList<Integer> imgResList = new ArrayList<>();
                imgResList.add(R.mipmap.banner);
                imgResList.add(R.mipmap.banner);
                ImageBrowseIntent.showResIdImageBrowse(this,imgResList,0);
                break;
            case R.id.btn3:
                ImageBrowseIntent.showUrlImageBrowse(this,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575018813844&di=a3f8bff33f80b48d37efb4fc653e83b8&imgtype=0&src=http%3A%2F%2Fphotocdn.sohu.com%2F20120518%2FImg343514676.jpg");
                break;
            case R.id.btn4:
                ArrayList<String> imgList = new ArrayList<>();
                imgList.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=1378180115,2478582402&fm=26&gp=0.jpg");
                imgList.add("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=372592629,1916025114&fm=26&gp=0.jpg");
                imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575018952540&di=6d19161da846bd91e3c49d21d9b658f7&imgtype=0&src=http%3A%2F%2Fwww.ttpaihang.com%2Fimage%2Fvote%2Fu130621201158883376.jpg");
                imgList.add("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1575019063778&di=ec06bc9b3e0557f1381172aec1bf01d6&imgtype=0&src=http%3A%2F%2Fn.sinaimg.cn%2Ffront%2F296%2Fw640h456%2F20190125%2FAUA1-hsccyrs7375041.jpg");
                ImageBrowseIntent.showUrlImageBrowse(this,imgList,2);
                break;
        }
    }
}
