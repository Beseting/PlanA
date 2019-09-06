package com.cdbhe.plib.utils;

import android.content.Context;
import android.content.DialogInterface;
import androidx.appcompat.app.AlertDialog;

import com.cdbhe.plib.R;


/**
 * Created by Kevin on 2018/2/1.
 */

public class AlertUtils {
    public static AlertDialog.Builder showAlert(Context context, String title, String content, DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder alert = new AlertDialog.Builder(context);
        alert.setTitle(title);
        alert.setMessage(content);
        alert.setNegativeButton(context.getString(R.string.cancel), null);
        alert.setPositiveButton(context.getString(R.string.ok), onClickListener);
        alert.show();
        return alert;
    }
}