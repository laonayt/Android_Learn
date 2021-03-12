package com.we.androidwezhibo.utils;

import com.we.androidwezhibo.login.LoginBean;

public class HelpTool {
    //单例
    private static HelpTool tool;
    public static HelpTool getInstance() {
        if (tool == null) {
            synchronized (Object.class){
                tool = new HelpTool();
            }
        }
        return tool;
    }

    //属性
    public LoginBean loginBean;
//    public String ip;
}
