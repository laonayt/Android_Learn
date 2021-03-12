package com.we.androidwezhibo.home.livelist;

import android.content.Intent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.base.BaseActivity;
import com.we.androidwezhibo.home.livedetail.LiveDetailActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LiveListActivity extends BaseActivity<LiveListPresenter> implements LiveListView, OnRefreshListener, OnRefreshLoadMoreListener {

    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_lives)
    RecyclerView rvRecycle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    private LiveListAdapter mAdapter;
    private List<LiveListBean.LiveListItem> mLiveList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_live_list;
    }

    @Override
    protected LiveListPresenter getPresenter() {
        return new LiveListPresenter(this);
    }

    @Override
    protected void initData() {
        tvTitle.setText("直播");

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvRecycle.setLayoutManager(lm);

        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.autoRefresh();

        mAdapter = new LiveListAdapter(mLiveList);
        mAdapter.setItemClickListener(new LiveListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(LiveListBean.LiveListItem item) {
                Intent intent = new Intent(LiveListActivity.this, LiveDetailActivity.class);
                startActivity(intent);
            }
        });
        rvRecycle.setAdapter(mAdapter);
    }



    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    //SmartRefresh
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.requestLiveListData();
    }

    //view 的回调
    @Override
    public void onGetNewsListSuccess(List<LiveListBean.LiveListItem> liveList) {
        if (liveList != null) {
            mLiveList.addAll(liveList);
        }
        mAdapter.notifyDataSetChanged();
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    @Override
    public void onError() {

    }
}
