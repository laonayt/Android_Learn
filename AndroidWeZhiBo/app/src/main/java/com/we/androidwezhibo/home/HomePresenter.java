package com.we.androidwezhibo.home;

import com.we.androidwezhibo.base.BasePresenter;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.network.RetrofitTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter extends BasePresenter<HomeView> {

    public HomePresenter(HomeView view) {
        super(view);
    }

    public void requestData(int pageIndex) {
        Map<String, Object> params = new HashMap<>();
        params.put("pageIndex", pageIndex);
        params.put("pageSize", 5);

        Call<HttpResponse<HomeListBean>> responseCall = RetrofitTool.getInstance().retrofitService.recommendRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<HomeListBean>>() {
            @Override
            public void onResponse(Call<HttpResponse<HomeListBean>> call, Response<HttpResponse<HomeListBean>> response) {
                List<HomeListBean.HomeListItem> list = response.body().data.data;
                mView.onRequestSuccess(list);
            }

            @Override
            public void onFailure(Call<HttpResponse<HomeListBean>> call, Throwable t) {

            }
        });
    }
}
