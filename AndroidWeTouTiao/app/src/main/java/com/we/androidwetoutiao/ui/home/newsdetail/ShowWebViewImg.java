package com.we.androidwetoutiao.ui.home.newsdetail;

import android.content.Context;
import android.content.Intent;
import android.webkit.JavascriptInterface;

import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

public class ShowWebViewImg {
    private Context context;
    private List<String> imgUrls = new ArrayList<>();


    public ShowWebViewImg(Context context) {
        this.context = context;
    }

    @JavascriptInterface
    public void openImg(String url){
        //String[] 和 ArrayList<String> 的区别
//        Intent intent = new Intent(context, ImageViewPagerActivity.class);
//        intent.putStringArrayListExtra("'imgList'", (ArrayList<String>) imgUrls);
//        context.startActivity(intent);
        KLog.e(imgUrls);
    }

    @JavascriptInterface
    public void getImgArray(String urlArray) {
        String[] urls = urlArray.split(";");
        for (String url : urls) {
            imgUrls.add(url);
        }

    }
}
