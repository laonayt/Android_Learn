package com.we.androidwetoutiao.api;

import java.util.List;

public class NewsResponse {
    public List<NewsData> data;

    public class NewsData {
        public String content;
        public String code;
    }
}
