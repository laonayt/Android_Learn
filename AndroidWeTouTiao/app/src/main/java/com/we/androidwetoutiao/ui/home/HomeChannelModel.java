package com.we.androidwetoutiao.ui.home;

import java.io.Serializable;

public class HomeChannelModel implements Serializable {
    public String title;
    public String channelCode;

    public HomeChannelModel(String title, String channelCode) {
        this.title = title;
        this.channelCode = channelCode;
    }
}
