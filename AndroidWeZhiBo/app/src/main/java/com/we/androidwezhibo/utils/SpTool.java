package com.we.androidwezhibo.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.we.androidwezhibo.common.App;

//调用直接SpTool.saveString 为什么？

public class SpTool {
    public static void saveString(String value, String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putString(key, value).commit();
    }

    public static String getString(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getString(key,"");
    }

    public static void saveBool(boolean value, String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }

    public static boolean getBool(String key) {
        SharedPreferences sp = App.getContext().getSharedPreferences("config", Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }
}
