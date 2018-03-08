package com.cdbhe.plib.http.file;

import android.content.Context;

import com.cdbhe.plib.R;
import com.cdbhe.plib.utils.LogUtils;
import com.cdbhe.plib.utils.ToastUtils;
import com.liulishuo.filedownloader.BaseDownloadTask;
import com.liulishuo.filedownloader.FileDownloadListener;
import com.liulishuo.filedownloader.FileDownloader;

import java.io.File;
import java.text.DecimalFormat;

/**
 * Created by Kevin on 2018/3/7.
 */

public class FileDownloadHelper {
    /**
     * 下载文件
     *
     * @param context
     * @param url
     * @param path
     * @param fileDownloadCallback
     */
    public static int download(final Context context, String url, final String path, final FileDownloadCallback fileDownloadCallback) {
        FileDownloader.setup(context);
        int taskId = FileDownloader.getImpl().create(url)
                .setPath(path)
                .setListener(new FileDownloadListener() {
                    @Override
                    protected void pending(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.d("-->pending");
                    }

                    @Override
                    protected void connected(BaseDownloadTask task, String etag, boolean isContinue, int soFarBytes, int totalBytes) {
                        LogUtils.d("-->connected");
                    }

                    @Override
                    protected void progress(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        double percent;
                        if (soFarBytes == totalBytes) {
                            percent = 1.0;
                        } else {
                            percent = Double.parseDouble(new DecimalFormat("######0.00").format(soFarBytes * 1.0 / totalBytes));
                        }
                        fileDownloadCallback.onProgress(soFarBytes, totalBytes, percent);
                        LogUtils.d("进度：" + soFarBytes + ",总大小：" + totalBytes + ",百分比：" + percent*100 + "%");
                    }

                    @Override
                    protected void blockComplete(BaseDownloadTask task) {
                        LogUtils.d("-->blockComplete");
                    }

                    @Override
                    protected void retry(final BaseDownloadTask task, final Throwable ex, final int retryingTimes, final int soFarBytes) {
                        LogUtils.d("-->retry");
                    }

                    @Override
                    protected void completed(BaseDownloadTask task) {
                        fileDownloadCallback.completed(new File(path));
                        LogUtils.d("-->completed");
                    }

                    @Override
                    protected void paused(BaseDownloadTask task, int soFarBytes, int totalBytes) {
                        LogUtils.d("-->paused");
                    }

                    @Override
                    protected void error(BaseDownloadTask task, Throwable e) {
                        ToastUtils.showShort(context, context.getString(R.string.file_download_error));
                        LogUtils.e("Error:" + e.getMessage());
                    }

                    @Override
                    protected void warn(BaseDownloadTask task) {
                        ToastUtils.showShort(context, context.getString(R.string.file_download_warn));
                    }
                }).start();
        return taskId;
    }

    /**
     * 暂停下载任务
     * @param taskId
     */
    public static void pauseDownloadTask(int taskId) {
        FileDownloader.getImpl().pause(taskId);
    }

    /**
     * 暂停所有下载任务
     */
    public static void pauseAllDownloadTask(){
        FileDownloader.getImpl().pauseAll();
    }
}
