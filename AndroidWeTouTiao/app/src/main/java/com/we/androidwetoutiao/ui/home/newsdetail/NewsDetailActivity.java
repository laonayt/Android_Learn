package com.we.androidwetoutiao.ui.home.newsdetail;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.socks.library.KLog;
import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.api.ApiRetrofit;
import com.we.androidwetoutiao.api.ApiService;
import com.we.androidwetoutiao.api.ResultResponse;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailActivity extends AppCompatActivity {
    @BindView(R.id.rv_comment)
    RecyclerView rvComment;
    private NewsDetailFrameLayout newsDetailFrameLayout;
    private CommentListAdapter commentListAdapter;
    private List<CommentListModel> commentModelList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        ButterKnife.bind(this);

        initData();
    }

    @OnClick({R.id.iv_back, R.id.iv_detail})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_detail:
                break;
        }
    }

    private void initData() {
        //mock
        CommentListModel commentModel = new CommentListModel();
        commentModel.title = "不是不行，就是不行";
        commentModelList.add(commentModel);

        //1
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getBaseContext());
        linearLayoutManager.setStackFromEnd(true);
        rvComment.setLayoutManager(linearLayoutManager);

        //2
        commentListAdapter = new CommentListAdapter(commentModelList);

        //3
        newsDetailFrameLayout = new NewsDetailFrameLayout(this);
        commentListAdapter.setHeaderView(newsDetailFrameLayout);

        rvComment.setAdapter(commentListAdapter);

        //4
        String url = getIntent().getStringExtra("url");
        newsDetailFrameLayout.requestData(url);
    }
}
