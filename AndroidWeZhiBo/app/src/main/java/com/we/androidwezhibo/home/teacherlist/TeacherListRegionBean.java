package com.we.androidwezhibo.home.teacherlist;

import java.util.List;

public class TeacherListRegionBean {
    public int total;
    public List<TeacherListRegionItem> list;

    public class TeacherListRegionItem {
        public String teacherName;
        public String pictureUrl;
        public String loginName;
    }

}
