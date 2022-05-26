package com.ym.plib.utils;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Kevin on 2018/3/8.
 */

public class FileUtils {

    /**
     * 获取文件上传所需Part
     * @param file
     * @return
     */
    public static MultipartBody.Part getUploadFilePart(File file){
        return getPart(file,"file");
    }

    /**
     * 获取文件上传所需Part
     * @param file
     * @param partName
     * @return
     */
    public static MultipartBody.Part getUploadFilePart(File file,String partName){
        return getPart(file,partName);
    }

    /**
     * Part获取
     * @param file
     * @param partName
     * @return
     */
    private static MultipartBody.Part getPart(File file,String partName){
        // 创建 RequestBody，用于封装构建RequestBody
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        // MultipartBody.Part  和后端约定好Key，这里的partName是用file
        return MultipartBody.Part.createFormData(partName, file.getName(), requestFile);
    }
}
