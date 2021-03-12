package com.we.androidwezhibo.home.teacherlist;
import com.we.androidwezhibo.base.BasePresenter;
import com.we.androidwezhibo.login.LoginBean;
import com.we.androidwezhibo.network.HttpResponse;
import com.we.androidwezhibo.utils.HelpTool;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TeacherListPresenter extends BasePresenter<TeacherListView> {
    public TeacherListPresenter(TeacherListView view) {
        super(view);
    }

    //热门名师
    public void requestHotData() {
        LoginBean loginBean = HelpTool.getInstance().loginBean;
        Map<String, Object> params = new HashMap<>();
        params.put("code", loginBean.code);

        Call<HttpResponse<List<TeacherListHotBean>>> responseCall = retrofitService().hotTeacherListRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<List<TeacherListHotBean>>>() {
            @Override
            public void onResponse(Call<HttpResponse<List<TeacherListHotBean>>> call, Response<HttpResponse<List<TeacherListHotBean>>> response) {
                List<TeacherListHotBean> list = response.body().data;
                mView.onRequestHotSuccess(list);
            }

            @Override
            public void onFailure(Call<HttpResponse<List<TeacherListHotBean>>> call, Throwable t) {

            }
        });

    }

    //区域名师
    public void requestRegionData() {
        LoginBean loginBean = HelpTool.getInstance().loginBean;

        Map<String, Object> keywordsP = new HashMap<>();
        keywordsP.put("code", loginBean.code);
        keywordsP.put("teacherType", 1);

        Map<String, Object> pageP = new HashMap<>();
        pageP.put("pageIndex", 1);
        pageP.put("pageSize", 6);

        Map<String, Object> params = new HashMap<>();
        params.put("keywords", keywordsP);
        params.put("page", pageP);

        Call<HttpResponse<TeacherListRegionBean>> responseCall = retrofitService().regionTeacherListRequest(params);
        responseCall.enqueue(new Callback<HttpResponse<TeacherListRegionBean>>() {
            @Override
            public void onResponse(Call<HttpResponse<TeacherListRegionBean>> call, Response<HttpResponse<TeacherListRegionBean>> response) {
                List<TeacherListRegionBean.TeacherListRegionItem> list = response.body().data.list;
                mView.onRequestRegionSuccess(list);
            }

            @Override
            public void onFailure(Call<HttpResponse<TeacherListRegionBean>> call, Throwable t) {

            }
        });
    }

}
