package com.cdbhe.plib.http.request;

import androidx.annotation.NonNull;

import com.cdbhe.plib.http.file.download.FileDownLoadObserver;
import com.cdbhe.plib.http.file.download.FileDownloadCallback;
import com.cdbhe.plib.http.retrofit.ApiService;
import com.cdbhe.plib.utils.DateUtils;

import java.io.File;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DownloadRequest {
    private String url;
    private ApiService apiService;

    public DownloadRequest(String url, ApiService apiService) {
        this.url = url;
        this.apiService = apiService;
    }

    public String execute(final String filePath, FileDownloadCallback<File> fileFileDownloadCallback) {
        String taskId = String.valueOf(DateUtils.getTimeInMillis());
        final FileDownLoadObserver fileDownLoadObserver = new FileDownLoadObserver(taskId,fileFileDownloadCallback);
        apiService.downloadFile(url).subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .observeOn(Schedulers.computation()) // 用于计算任务
                .map(new Function<ResponseBody, File>() {
                    @Override
                    public File apply(@NonNull ResponseBody responseBody) throws Exception {
                        return fileDownLoadObserver.saveFile(responseBody, filePath);
                    }
                })
                .subscribe(fileDownLoadObserver);
        return taskId;
    }
}
