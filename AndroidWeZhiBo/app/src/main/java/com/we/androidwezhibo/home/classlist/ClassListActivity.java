package com.we.androidwezhibo.home.classlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.listener.OnRefreshListener;
import com.scwang.smart.refresh.layout.listener.OnRefreshLoadMoreListener;
import com.we.androidwezhibo.R;
import com.we.androidwezhibo.home.classdetail.ClassDetailActivity;
import com.we.androidwezhibo.login.LoginBean;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;
import com.we.androidwezhibo.utils.HelpTool;

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

public class ClassListActivity extends AppCompatActivity implements OnRefreshListener, OnRefreshLoadMoreListener {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.rv_list)
    RecyclerView rvRecycle;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    private List<ClassListBean.ClassListItem> mClassList = new ArrayList<>();
    private ClassListAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);
        ButterKnife.bind(this);

        initData();
    }

    //初始化数据
    private void initData() {
        tvTitle.setText("课程列表");

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        refreshLayout.autoRefresh();

        LinearLayoutManager lm = new LinearLayoutManager(this);
        rvRecycle.setLayoutManager(lm);

        mAdapter = new ClassListAdapter(mClassList);
        mAdapter.setmItemClickListener(new ClassListAdapter.ItemClickListener() {
            @Override
            public void onItemClick(ClassListBean.ClassListItem item) {
                Intent intent = new Intent(ClassListActivity.this, ClassDetailActivity.class);
                intent.putExtra("curriculumId", item.curriculumId);
                intent.putExtra("isLive", false);
                startActivity(intent);
            }
        });
        rvRecycle.setAdapter(mAdapter);
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }

    //下拉刷新
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {

    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        requestData();
    }

    //请求数据
    private void requestData() {
        LoginBean loginBean = HelpTool.getInstance().loginBean;

        Map<String, Object> keyWordsP = new HashMap<>();
        keyWordsP.put("code", loginBean.code);
        keyWordsP.put("teacherType", 1);

        Map<String, Object> pageP = new HashMap<>();
        pageP.put("pageIndex", 1);
        pageP.put("pageSize", 9);

        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keyWordsP);
        pageP.put("page", pageP);

        Call<HttpResponse<ClassListBean>> responseCall = RetrofitTool.getInstance().retrofitService.tinyClassListRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<ClassListBean>>() {
            @Override
            public void onResponse(Call<HttpResponse<ClassListBean>> call, Response<HttpResponse<ClassListBean>> response) {
                List<ClassListBean.ClassListItem> list = response.body().data.data;
                mClassList.addAll(list);//必须这样add？
                mAdapter.notifyDataSetChanged();
                refreshLayout.finishRefresh();
                refreshLayout.finishLoadMore();
            }

            @Override
            public void onFailure(Call<HttpResponse<ClassListBean>> call, Throwable t) {

            }
        });
    }

}
