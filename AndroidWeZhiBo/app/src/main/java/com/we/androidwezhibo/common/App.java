package com.we.androidwezhibo.common;

import android.app.Application;
import android.content.Context;

import com.we.androidwezhibo.utils.ToastUtils;

public class App extends Application {
    private static Context context;//整个程序的上下文

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        initData();
    }

    public static Context getContext() {
        return context;
    }

    private void initData() {
        ToastUtils.init(context);
    }
}
