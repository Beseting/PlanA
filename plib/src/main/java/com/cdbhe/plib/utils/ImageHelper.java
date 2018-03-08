package com.cdbhe.plib.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Kevin on 2018/2/25.
 */

public class ImageHelper {
    /**
     * @param filePath
     * @return
     * @throws IOException
     * 压缩图片
     */
    public static Bitmap CompressionImage(String filePath) throws IOException {
        File _file = new File(filePath);
        if(_file == null || !_file.exists()){
            return null;
        }
        // 创建一个字节缓冲输入流
        BufferedInputStream in = new BufferedInputStream(new FileInputStream(_file));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        // 根据流返回一个位图也就是bitmap，当options.inJustDecodeBounds = true的时候不需要完全解码，
        // 它仅仅会把它的宽，高取回来给你，这样就不会占用太多的内存，也就不会那么频繁的发生OOM了
        BitmapFactory.decodeStream(in, null, options);
        //关闭流
        in.close();
        int i = 0;
        Bitmap bitmap = null;
        while (true) {
            // options.outWidth >> i 。右移运算符，num >> 1,相当于num除以2
            if ((options.outWidth >> i <= 1000) && (options.outHeight >> i <= 1000)) {
                //得到一个输入流
                in = new BufferedInputStream(new FileInputStream(_file));
                //为了解决图片解码时候出现SanpleSize错误，设置恰当的inSampleSize可以使BitmapFactory分配更少的空间以消除该错误
                //你将 inSampleSize 赋值为2,那就是每隔2行采1行,每隔2列采一列,那你解析出的图片就是原图大小的1/4.
                // Math.pow(2.0D, i)次方运算，2的i次方是多少
                options.inSampleSize = (int) Math.pow(2.0D, i);
                // 这里之前设置为了true，所以要改为false，否则就创建不出图片
                options.inJustDecodeBounds = false;
                bitmap = BitmapFactory.decodeStream(in, null, options);
                break;
            }
            i += 1;
        }
        return bitmap;
    }
}