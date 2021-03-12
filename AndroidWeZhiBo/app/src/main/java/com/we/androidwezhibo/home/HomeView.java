package com.we.androidwezhibo.home;

import com.we.androidwezhibo.base.BaseView;

import java.util.List;

public interface HomeView  {
    void onRequestSuccess(List<HomeListBean.HomeListItem> dataList);
}
