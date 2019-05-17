package com.cdbhe.plana.mvvm.upload_download.biz;

import android.widget.Button;
import android.widget.ProgressBar;

import com.cdbhe.plib.http.retrofit.IBaseBiz;

public interface IUploadDownloadBiz extends IBaseBiz {
    Button getUploadBtn();
    Button getCancelUploadBtn();
    ProgressBar getUploadProgressBar();
    Button getDownloadBtn();
    Button getCancelDownloadBtn();
    ProgressBar getDownloadProgressBar();
}
