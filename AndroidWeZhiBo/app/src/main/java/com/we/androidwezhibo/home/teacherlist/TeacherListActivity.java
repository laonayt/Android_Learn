package com.we.androidwezhibo.home.teacherlist;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;
import com.we.androidwezhibo.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class TeacherListActivity extends BaseActivity<TeacherListPresenter> implements TeacherListView{
    @BindView(R.id.rv_hot)
    RecyclerView rvRecycleHot;
    @BindView(R.id.rv_region)
    RecyclerView rvRecycleRegion;

    private TeacherListHotAdapter mHotAdapter;
    private TeacherListRegionAdapter mRegionAdapter;
    private List<TeacherListHotBean> mHotList = new ArrayList<>();
    private List<TeacherListRegionBean.TeacherListRegionItem> mRegionList = new ArrayList<>();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list;
    }

    @Override
    protected TeacherListPresenter getPresenter() {
        return new TeacherListPresenter(this);
    }

    //初始化
    @Override
    protected void initData() {
        mPresenter.requestHotData();
        mPresenter.requestRegionData();

        GridLayoutManager hotGm = new GridLayoutManager(this,3);
        rvRecycleHot.setLayoutManager(hotGm);
        mHotAdapter = new TeacherListHotAdapter(mHotList);
        rvRecycleHot.setAdapter(mHotAdapter);

        GridLayoutManager regionGm = new GridLayoutManager(this,3);
        rvRecycleRegion.setLayoutManager(regionGm);
        mRegionAdapter = new TeacherListRegionAdapter(mRegionList);
        rvRecycleRegion.setAdapter(mRegionAdapter);
    }

    //请求成功
    @Override
    public void onRequestHotSuccess(List<TeacherListHotBean> dataList) {
        mHotList.addAll(dataList);
        mHotAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRequestRegionSuccess(List<TeacherListRegionBean.TeacherListRegionItem> dataList) {
        mRegionList.addAll(dataList);
        mRegionAdapter.notifyDataSetChanged();
    }

}
