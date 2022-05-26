package com.ym.plib.http.file.upload;

import com.ym.plib.http.common.RxManager;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class FileUploadObserver<T> implements Observer<T> {
    private String taskId;
    private FileUploadCallback fileUploadCallback;

    public FileUploadObserver(String taskId, FileUploadCallback fileUploadCallback) {
        this.taskId = taskId;
        this.fileUploadCallback = fileUploadCallback;
    }

    @Override
    public void onNext(T t) {
        fileUploadCallback.onUpLoadSuccess(t);
    }

    @Override
    public void onError(Throwable e) {
        fileUploadCallback.onUpLoadFail(e);
    }

    @Override
    public void onComplete() {
    }

    @Override
    public void onSubscribe(Disposable d) {
        RxManager.getInstance().add(taskId, d);
    }

    //监听进度的改变
    public void onProgressChange(long bytesWritten, long contentLength) {
        fileUploadCallback.onProgress(bytesWritten, contentLength);
    }
}
