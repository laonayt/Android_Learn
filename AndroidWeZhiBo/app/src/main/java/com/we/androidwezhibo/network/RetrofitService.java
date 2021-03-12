package com.we.androidwezhibo.network;

import com.we.androidwezhibo.home.HomeListBean;
import com.we.androidwezhibo.home.classlist.ClassListBean;
import com.we.androidwezhibo.home.classdetail.CommentListBean;
import com.we.androidwezhibo.home.livelist.LiveListBean;
import com.we.androidwezhibo.home.teacherlist.TeacherListHotBean;
import com.we.androidwezhibo.home.teacherlist.TeacherListRegionBean;
import com.we.androidwezhibo.login.LoginBean;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

public interface RetrofitService {
    //检查ip
    @GET("/platform-app/live/checkip")
    Call<HttpResponse> checkIpRequest();

    //登录
    @GET("/platform-app/app/login")
    Call<HttpResponse<LoginBean>> loginRequest(@QueryMap Map<String, Object> map);

    //查询精品推荐
    @POST("/platform-app/resource/elegant/recommend")
    Call<HttpResponse<HomeListBean>> recommendRequest(@Body Map<String, Object> map);

    //查询优课微课列表
    @POST("/platform-app/resource/list")
    Call<HttpResponse<ClassListBean>> tinyClassListRequest(@Body Map<String, Object> map);

    //热门名师
    @GET("/platform-app/hot/teacher")
    Call<HttpResponse<List<TeacherListHotBean>>> hotTeacherListRequest(@QueryMap Map<String, Object> map);

    //区域名师
    @POST("/platform-app/region/teacher")
    Call<HttpResponse<TeacherListRegionBean>> regionTeacherListRequest(@Body Map<String, Object> map);

    //查询正在直播和即将直播的视频
    @GET("/platform-app/live/living/broadcasted")
    Call<HttpResponse<LiveListBean>> liveListRequest(@QueryMap Map<String, Object> map);

    //评论列表
    @POST("/platform-app/resource/message")
    Call<HttpResponse<CommentListBean>> commentListRequest(@Body Map<String, Object> map);

    //添加评论
    @POST("/platform-app/resource/message/insert")
    Call<HttpResponse> addCommentRequest(@Body Map<String, Object> map);

    //删除评论
    @POST("/platform-app/resource/message/delete")
    Call<HttpResponse> deleteCommentRequest(@Body Map<String, Object> map);
}
