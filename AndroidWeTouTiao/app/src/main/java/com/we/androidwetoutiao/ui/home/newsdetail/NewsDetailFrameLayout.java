package com.we.androidwetoutiao.ui.home.newsdetail;

import android.annotation.SuppressLint;
import android.content.Context;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.socks.library.KLog;
import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.api.ApiRetrofit;
import com.we.androidwetoutiao.api.ApiService;
import com.we.androidwetoutiao.api.ResultResponse;

import butterknife.BindView;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsDetailFrameLayout extends FrameLayout {
    @BindView(R.id.tv_author)
    TextView tvAuthor;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.wv_content)
    WebView wvContent;

    private Context mContext;

    public NewsDetailFrameLayout(@NonNull Context context) {
        super(context);
        mContext = context;
        inflate(context, R.layout.frame_layout_news_detail, this);
    }

    public void requestData(String url) {
        ApiService apiService = ApiRetrofit.getInstance().getApiService();
        Observable<ResultResponse<NewsDetailModel>> observable = apiService.getNewsDetail(url);

        observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultResponse<NewsDetailModel>>() {
                    @Override
                    public void onCompleted() {
                        KLog.e("onCompleted");
                    }

                    @Override
                    public void onError(Throwable e) {
                        KLog.e(e);
                    }

                    @Override
                    public void onNext(ResultResponse<NewsDetailModel> response) {
                        setDetailData(response.data);
                    }
                });
    }

    private void setDetailData(NewsDetailModel detailData) {
//        tvAuthor.setText(detailData.media_user.screen_name);
        tvTime.setText("2020-08-26 00:00");
        setHtmlData(detailData.content);
    }

    @SuppressLint("JavascriptInterface")
    private void setHtmlData(String content) {
        String htmlPart1 = "<!DOCTYPE HTML html>\n" +
                "<head><meta charset=\"utf-8\"/>\n" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, minimum-scale=1.0, user-scalable=no\"/>\n" +
                "</head>\n" +
                "<body>\n" +
                "<style> \n" +
                "img{width:100%!important;height:auto!important}\n" +
                " </style>";
        String htmlPart2 = "</body></html>";

        String html = htmlPart1 + content + htmlPart2;
        wvContent.loadData(html, "text/html", "UTF-8");

        wvContent.getSettings().setJavaScriptEnabled(true);
        wvContent.addJavascriptInterface(new ShowWebViewImg(mContext), "chaychan");
        wvContent.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                addJs(view);
            }
        });
    }

    private void addJs(WebView webView) {
        webView.loadUrl("javascript:(function  pic(){" +
                "var imgList = \"\";" +
                "var imgs = document.getElementsByTagName(\"img\");" +
                "for(var i=0;i<imgs.length;i++){" +
                "var img = imgs[i];" +
                "imgList = imgList + img.src +\";\";" +
                "img.onclick = function(){" +
                "window.chaychan.openImg(this.src);" +
                "}" +
                "}" +
                "window.chaychan.getImgArray(imgList);" +
                "})()");
    }
}
