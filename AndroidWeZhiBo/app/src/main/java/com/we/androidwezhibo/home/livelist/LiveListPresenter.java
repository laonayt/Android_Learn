package com.we.androidwezhibo.home.livelist;

import com.socks.library.KLog;
import com.we.androidwezhibo.base.BasePresenter;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.utils.HelpTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveListPresenter extends BasePresenter<LiveListView> {

    public LiveListPresenter(LiveListView view) {
        super(view);
    }

    public void requestLiveListData() {
        String code = HelpTool.getInstance().loginBean.code;

        Map<String, Object> params = new HashMap<>();
        params.put("code", code);
        params.put("pageIndex", "1");
        params.put("pageSize", "100");

        Call<HttpResponse<LiveListBean>> responseCall = retrofitService().liveListRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<LiveListBean>>() {

            @Override
            public void onResponse(Call<HttpResponse<LiveListBean>> call, Response<HttpResponse<LiveListBean>> response) {
                List<LiveListBean.LiveListItem> livingList = response.body().data.living.list;
                mView.onGetNewsListSuccess(livingList);
            }

            @Override
            public void onFailure(Call<HttpResponse<LiveListBean>> call, Throwable t) {
                KLog.e("err");
                mView.onError();
            }
        });
    }
}
