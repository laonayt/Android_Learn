package com.we.androidwetoutiao.ui.home.newsdetail;

public class NewsDetailModel {
    public String content;
    public MediaUser media_user;

    public class MediaUser {
        public boolean no_display_pgc_icon;
        public String avatar_url;
        public String id;
        public String screen_name;
    }

}
