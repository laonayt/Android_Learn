package com.we.androidwezhibo.home.teacherlist;

import java.util.List;

public interface TeacherListView {
    public void onRequestHotSuccess(List<TeacherListHotBean> dataList);
    public void onRequestRegionSuccess(List<TeacherListRegionBean.TeacherListRegionItem> dataList);
}
