package com.ym.plib.http.file.download;

/**
 * Created by Kevin on 2018/3/7.
 */

public interface FileDownloadCallback<T> {
    void onSuccess(T t);
    void onFail(Throwable throwable);
    void onProgress(long current,long total);
}
