package com.we.androidwezhibo.home.classdetail;

import java.util.List;

public class CommentListBean {
    public int total;
    public List<CommentListItem> list;

    public class CommentListItem {
        public String resourceid;
        public String name;
        public String content;
        public String createdate;
        public String createuser;
    }
}
