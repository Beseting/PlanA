package com.cdbhe.plana.mvvm.upload_download.view;

import android.Manifest;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.cdbhe.plana.R;
import com.cdbhe.plana.base.MyBaseActivity;
import com.cdbhe.plana.mvvm.upload_download.biz.IUploadDownloadBiz;
import com.cdbhe.plana.mvvm.upload_download.vm.UploadDownloadVm;
import com.cdbhe.plib.utils.ToastUtils;

import java.security.Permission;

import butterknife.BindView;
import butterknife.OnClick;

public class UploadDownloadActivity extends MyBaseActivity implements IUploadDownloadBiz {
    @BindView(R.id.uploadBtn)
    Button uploadBtn;
    @BindView(R.id.cancelUploadBtn)
    Button cancelUploadBtn;
    @BindView(R.id.uploadProgress)
    ProgressBar uploadProgress;
    @BindView(R.id.cancelDownloadBtn)
    Button cancelDownloadBtn;
    @BindView(R.id.downloadBtn)
    Button downloadBtn;
    @BindView(R.id.downloadProgress)
    ProgressBar downloadProgress;
    private UploadDownloadVm vm;
    @Override
    public int getLayoutResId() {
        return R.layout.activity_upload_download;
    }

    @Override
    public void initView(Bundle var1) {
        setTitle("文件上传/下载");
        vm = new UploadDownloadVm(this);
    }

    @OnClick({R.id.uploadBtn,R.id.cancelUploadBtn,R.id.downloadBtn,R.id.cancelDownloadBtn})
    public void click(View view){
        if(view.getId() == R.id.uploadBtn){//上传
            requestPermission(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},1);
        }else if(view.getId() == R.id.cancelUploadBtn){//取消上传
            vm.cancelUpload();
        }else if(view.getId()==R.id.downloadBtn){// 下载
            requestPermission(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},2);
        }else{// 取消下载
            vm.cancelDownload();
        }
    }

    @Override
    public Button getUploadBtn() {
        return uploadBtn;
    }

    @Override
    public Button getCancelUploadBtn() {
        return cancelUploadBtn;
    }

    @Override
    public ProgressBar getUploadProgressBar() {
        return uploadProgress;
    }

    @Override
    public Button getDownloadBtn() {
        return downloadBtn;
    }

    @Override
    public Button getCancelDownloadBtn() {
        return cancelDownloadBtn;
    }

    @Override
    public ProgressBar getDownloadProgressBar() {
        return downloadProgress;
    }

    @Override
    public void permissionSucceed(int code) {
        super.permissionSucceed(code);
        if(code == 1) {
            vm.upload();
        }else{
            vm.download();
        }
    }

    @Override
    public void permissionFailing(int code) {
        super.permissionFailing(code);
        ToastUtils.showShort(this,"权限获取失败");
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void toActivity(Object... objects) {

    }
}
