package com.we.androidwezhibo.login;

public class LoginBean {
    public String appAccessToken;
    public String id;
    public String name;
    public String loginName;
    public String code;
    private boolean isVip;

    public boolean isVip() {
        return isVip;
    }

    public void setVip(boolean vip) {
        isVip = vip;
    }
}
