package com.we.androidwetoutiao.base;

import com.socks.library.KLog;

public class MyApp extends BaseApp {

    @Override
    public void onCreate() {
        super.onCreate();

        //**************************************相关第三方SDK的初始化等操作*************************************************
        KLog.init(true);//初始化KLog
//        LitePalApplication.initialize(getApplicationContext());//初始化litePal
//
//        registerActivityLifecycleCallbacks(ParallaxHelper.getInstance());
    }
}

