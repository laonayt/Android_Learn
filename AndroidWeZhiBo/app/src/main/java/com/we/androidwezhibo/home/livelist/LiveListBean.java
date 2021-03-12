package com.we.androidwezhibo.home.livelist;

import java.util.List;

public class LiveListBean {
    public LiveList living;
    public LiveList broadCasted;

    public class LiveList {
        public int total;
        public List<LiveListItem> list;
    }

    public class LiveListItem {
        public String id;
        public String name;
        public String userName;
        public String endDate;
        public int edition;
        public int book;
        public int chapter;
        public String imageurl;
        public int watchwatchNum;
        public String fromcode;
        public String beginDate;
        public String curriculumId;
        public String openType;
        public int type;   //-1, 标题 0,正在直播,1,即将直播
        public String source;
        public Integer resSource;
    }

}
