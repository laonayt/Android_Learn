package com.we.androidwezhibo.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnLoadMoreListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.base.BaseFragment;
import com.we.androidwezhibo.home.classlist.ClassListActivity;
import com.we.androidwezhibo.home.history.HistoryActivity;
import com.we.androidwezhibo.home.livelist.LiveListActivity;
import com.we.androidwezhibo.home.message.MessageActivity;
import com.we.androidwezhibo.home.teacherlist.TeacherListActivity;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends BaseFragment<HomePresenter> implements HomeView, OnRefreshListener, OnLoadMoreListener {
    @BindView(R.id.rv_list)
    RecyclerView rvRecycle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;

    HomeListAdapter mAdapter;
    List<HomeListBean.HomeListItem> mDataList = new ArrayList<>();
    private int pageIndex = 1;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home;
    }

    @Override
    protected HomePresenter getPresenter() {
        return new HomePresenter(this);
    }

    @Override
    protected void initData() {
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.autoRefresh();

        LinearLayoutManager lm = new LinearLayoutManager(getActivity());
        rvRecycle.setLayoutManager(lm);
        mAdapter = new HomeListAdapter(mDataList);
        rvRecycle.setAdapter(mAdapter);

    }

    @OnClick({R.id.ll_search, R.id.iv_history, R.id.iv_message, R.id.iv_filter, R.id.ll_high_quality, R.id.ll_small_class, R.id.ll_famous_teacher, R.id.ll_live})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.ll_search:
                break;
            case R.id.iv_history:
                intent = new Intent(getContext(), HistoryActivity.class);
                break;
            case R.id.iv_message:
                intent = new Intent(getContext(), MessageActivity.class);
                break;
            case R.id.iv_filter:
                break;
            case R.id.ll_high_quality:
                intent = new Intent(getContext(), ClassListActivity.class);
                break;
            case R.id.ll_small_class:
                intent = new Intent(getContext(), ClassListActivity.class);
                break;
            case R.id.ll_famous_teacher:
                intent = new Intent(getContext(), TeacherListActivity.class);
                break;
            case R.id.ll_live:
                intent = new Intent(getContext(), LiveListActivity.class);
                break;
        }
        startActivity(intent);
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        pageIndex = 1;
        mDataList.clear();
        mPresenter.requestData(pageIndex);
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        pageIndex++;
        mPresenter.requestData(pageIndex);
    }

    //请求成功
    @Override
    public void onRequestSuccess(List<HomeListBean.HomeListItem> dataList) {
        if (dataList.size() > 0) {
            mDataList.addAll(dataList);
            mAdapter.notifyDataSetChanged();
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMore();
        } else {
            refreshLayout.finishRefresh();
            refreshLayout.finishLoadMoreWithNoMoreData();
        }

    }

}
