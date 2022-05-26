package com.ym.plib.http.file.upload;

public interface FileUploadCallback<T> {
    //上传成功的回调
    void onUpLoadSuccess(T t);

    //上传失败回调
    void onUpLoadFail(Throwable e);

    //上传进度回调
    void onProgress(long bytesWritten, long contentLength);
}
