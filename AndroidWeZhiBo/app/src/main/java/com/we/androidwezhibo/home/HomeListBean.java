package com.we.androidwezhibo.home;

import java.util.List;
import java.util.Map;

public class HomeListBean {
    public int total;
    public List<HomeListItem> data;

    public class HomeListItem {
        public String curriculumId;
        public String author;
        public String createDate;
        public String cover;
        public Map<String,String> videoUrl;
    }
}
