package com.we.androidwezhibo.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by xu.wang
 * Date on 2016/6/1 09:22
 */
public class ToastUtils {
    private static Toast toast;

    public static Context mContext;

    public static void init(Context context){
        mContext = context;
    }

    public static void showToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        } else {
            //如果toast不为空，则直接更改当前toast的文本
            toast.setText(text + "");
        }
        toast.show();

    }

    public static void showToast(String text) {
        if (toast == null) {
            toast = Toast.makeText(mContext, text, Toast.LENGTH_SHORT);
        } else {
            //如果toast不为空，则直接更改当前toast的文本
            toast.setText(text + "");
        }
        toast.show();

    }

    public static void longToast(Context context, String text) {
        if (toast == null) {
            toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        } else {
            //如果toast不为空，则直接更改当前toast的文本
            toast.setText(text + "");
        }
        toast.show();

    }
}
