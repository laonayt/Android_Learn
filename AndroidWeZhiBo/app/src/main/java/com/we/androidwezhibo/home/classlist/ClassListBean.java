package com.we.androidwezhibo.home.classlist;

import java.util.List;

public class ClassListBean {
    public int total;
    public List<ClassListItem> data;

    public class ClassListItem {
        public String resourceName;
        public String author;
        public String curriculumId;
        public String cover;
        public String publishDate;
    }
}
