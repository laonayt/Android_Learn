package com.we.androidwezhibo.network;

import android.text.TextUtils;

import com.google.gson.GsonBuilder;
import com.socks.library.KLog;
import com.we.androidwezhibo.utils.HelpTool;
import com.we.androidwezhibo.utils.SpTool;

import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitTool {
    //属性
    public RetrofitService retrofitService;
    //单例
    private static RetrofitTool tool;
    public static RetrofitTool getInstance() {
        if (tool == null) {
            synchronized (Object.class) {
                tool = new RetrofitTool();

                String ip = SpTool.getString("ip");
                if (!TextUtils.isEmpty(ip)){
                    tool.initRetrofit(ip,8200);
                }
            }
        }
        return tool;
    }

    //初始化方法
    public void initRetrofit(String ip, int port) {
        String format = "http://%s:%d";
        String baseUrl = String.format(format, ip, port);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
//                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
//                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//支持RxJava
                .client(httpClient())
                .build();
        retrofitService = retrofit.create(RetrofitService.class);
    }

    private OkHttpClient httpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(mLogInterceptor)
                .addInterceptor(mHeaderInterceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
                .build();
        return client;
    }

    //chain 什么意思？ 最后加；?
    private Interceptor mHeaderInterceptor = chain -> {
        Request.Builder builder = chain.request().newBuilder();
        if (HelpTool.getInstance().loginBean != null) {
            builder.addHeader("userId", HelpTool.getInstance().loginBean.id);
            builder.addHeader("accessToken", HelpTool.getInstance().loginBean.appAccessToken);
        }
        return chain.proceed(builder.build());
    };

    /**请求访问quest和response拦截器*/
    private Interceptor mLogInterceptor = chain -> {
        Request request = chain.request();
        long startTime = System.currentTimeMillis();
        okhttp3.Response response = chain.proceed(chain.request());
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;
        okhttp3.MediaType mediaType = response.body().contentType();
        String content = response.body().string();
        KLog.e("----------Request Start----------------");
        KLog.e("| " + request.toString());
        KLog.e("| " + request.headers().toString());
        KLog.json("| Response:" + content);
        KLog.e("----------Request End:" + duration + "毫秒----------");
        return response.newBuilder()
                .body(okhttp3.ResponseBody.create(mediaType, content))
                .build();
    };
}
