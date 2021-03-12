package com.we.androidwezhibo.home.livelist;

import com.we.androidwezhibo.base.BaseView;

import java.util.List;

//extends /implements  <> 的区别
public interface LiveListView {
    void onGetNewsListSuccess(List<LiveListBean.LiveListItem> liveList);

    void  onError();
}
