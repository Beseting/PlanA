package com.ym.plana.mvvm.upload_download.vm;

import android.os.Environment;

import com.ym.plana.mvvm.upload_download.biz.IUploadDownloadBiz;
import com.ym.plib.http.file.download.FileDownloadCallback;
import com.ym.plib.http.file.upload.FileUploadCallback;
import com.ym.plib.http.retrofit.RetrofitClient;
import com.ym.plib.utils.LogUtils;

import java.io.File;

import okhttp3.ResponseBody;

public class UploadDownloadVm {

    private IUploadDownloadBiz iUploadDownloadBiz;
    private String uploadTaskId;
    private String downloadTaskId;

    public UploadDownloadVm(IUploadDownloadBiz iUploadDownloadBiz) {
        this.iUploadDownloadBiz = iUploadDownloadBiz;
    }

    public void upload() {
        iUploadDownloadBiz.getUploadBtn().setEnabled(false);
        iUploadDownloadBiz.getCancelUploadBtn().setEnabled(true);
        File file = new File(Environment.getExternalStorageDirectory() + File.separator + "test.png");
        uploadTaskId = RetrofitClient.getInstance()
                .upload("api/uploadFile")
                .param("token", "xxx")
                .upFile(file)
                .execute(new FileUploadCallback<ResponseBody>() {
                    @Override
                    public void onUpLoadSuccess(ResponseBody responseBody) {
                        iUploadDownloadBiz.getCancelUploadBtn().setEnabled(false);
                        iUploadDownloadBiz.getUploadBtn().setText("上传成功");
                    }

                    @Override
                    public void onUpLoadFail(Throwable e) {
                        LogUtils.e("上传失败");
                    }

                    @Override
                    public void onProgress(long bytesWritten, long contentLength) {
                        if (bytesWritten >= contentLength) {
                            iUploadDownloadBiz.getUploadProgressBar().setProgress(100);
                        } else {
                            int progress = (int) ((bytesWritten * 1.0) / contentLength * 100);
                            iUploadDownloadBiz.getUploadBtn().setText("上传进度：" + progress + "%");
                            iUploadDownloadBiz.getUploadProgressBar().setProgress(progress);
                        }
                    }
                });
    }

    public void download() {
        iUploadDownloadBiz.getDownloadBtn().setEnabled(false);
        iUploadDownloadBiz.getCancelDownloadBtn().setEnabled(true);
        String filePath = Environment.getExternalStorageDirectory() + File.separator + "test.flv";
        downloadTaskId = RetrofitClient.getInstance().download("upload/test.flv").execute(filePath, new FileDownloadCallback<File>() {
            @Override
            public void onSuccess(File file) {
                iUploadDownloadBiz.getActivity().runOnUiThread(() -> {
                    iUploadDownloadBiz.getCancelDownloadBtn().setEnabled(false);
                    iUploadDownloadBiz.getDownloadBtn().setText("下载成功");
                });
            }

            @Override
            public void onFail(Throwable throwable) {
                LogUtils.e("下载失败");
            }

            @Override
            public void onProgress(long current, long total) {
                iUploadDownloadBiz.getActivity().runOnUiThread(() -> {
                    if (current >= total) {
                        iUploadDownloadBiz.getDownloadProgressBar().setProgress(100);
                    } else {
                        int progress = (int) ((current * 1.0) / total * 100);
                        iUploadDownloadBiz.getDownloadBtn().setText("下载进度：" + progress + "%");
                        iUploadDownloadBiz.getDownloadProgressBar().setProgress(progress);
                    }
                });
            }
        });
    }

    public void cancelUpload() {
        iUploadDownloadBiz.getCancelUploadBtn().setEnabled(false);
        RetrofitClient.getInstance().cancelRequest(uploadTaskId);
    }

    public void cancelDownload() {
        iUploadDownloadBiz.getCancelDownloadBtn().setEnabled(false);
        RetrofitClient.getInstance().cancelRequest(downloadTaskId);
    }

    public void release(){
        this.iUploadDownloadBiz = null;
    }
}
