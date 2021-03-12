package com.we.androidwetoutiao.ui.home.newslist;

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

import com.google.gson.Gson;
import com.socks.library.KLog;
import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.api.ApiRetrofit;
import com.we.androidwetoutiao.api.ApiService;
import com.we.androidwetoutiao.api.NewsResponse;
import com.we.androidwetoutiao.ui.home.newsdetail.NewsDetailActivity;
import com.we.androidwetoutiao.ui.home.newsdetail.WebViewActivity;
import com.we.androidwetoutiao.utils.Constant;
import com.we.androidwetoutiao.utils.ListUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsListFragment extends Fragment {
    @BindView(R.id.rv_news)
    RecyclerView mRvNews;
    private NewsListAdapter mNewsAdapter;
    private List<NewsListModel> mNewsList = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);
        ButterKnife.bind(this,view);

        initData();
        getNewsList();
        return view;
    }

    //初始化
    private void initData() {
        //设置数据源
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setStackFromEnd(true);
        mRvNews.setLayoutManager(linearLayoutManager);


        mNewsAdapter = new NewsListAdapter(getContext(), mNewsList);
        mRvNews.setAdapter(mNewsAdapter);

        mNewsAdapter.setClickListener((holder, model, position) -> {
            Intent intent;
            //非视频新闻
            if (model.article_type == 1) {
                intent = new Intent(getActivity(), WebViewActivity.class);
                intent.putExtra("url","www.baidu.com");
            } else  {
                StringBuffer urlSb = new StringBuffer("http://m.toutiao.com/i");
                urlSb.append(model.item_id).append("/info/");
                String url = urlSb.toString();

                intent = new Intent(getActivity(), NewsDetailActivity.class);
                intent.putExtra("url",url);
            }
            startActivity(intent);
        });
    }

    //请求数据
    private void getNewsList(){
        String channelCode = getArguments().getString(Constant.CHANNEL_CODE);

        long currentTime = System.currentTimeMillis()/1000;

        ApiService mApiService = ApiRetrofit.getInstance().getApiService();

        Observable<NewsResponse> observable = mApiService.getNewsList(channelCode,currentTime,currentTime);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsResponse>() {
                    @Override
                    public void onCompleted() {
                        KLog.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                    }

                    @Override
                    public void onNext(NewsResponse response) {
                        List<NewsResponse.NewsData> data = response.data;
                        if (!ListUtils.isEmpty(data)){
                            for (NewsResponse.NewsData newsData : data) {
                                NewsListModel newsListModel = new Gson().fromJson(newsData.content, NewsListModel.class);
                                mNewsList.add(newsListModel);
                            }
                            mNewsAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }
}
