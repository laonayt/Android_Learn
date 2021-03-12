package com.we.androidwezhibo.base;

//只声明？ 和抽象类的区别？
public interface BaseView {
    void showToast(String msg);

    void showLoading(String loading);   //显示加载

    void dismissLoading();              //消失加载

    void writeLog(String TAG, String content);   //写本地日志
}
