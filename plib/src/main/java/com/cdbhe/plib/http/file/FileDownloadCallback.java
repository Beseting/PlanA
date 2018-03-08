package com.cdbhe.plib.http.file;

import java.io.File;

/**
 * Created by Kevin on 2018/3/7.
 */

public interface FileDownloadCallback {
    void onProgress(int progress,int total,double percent);
    void completed(File file);
}
