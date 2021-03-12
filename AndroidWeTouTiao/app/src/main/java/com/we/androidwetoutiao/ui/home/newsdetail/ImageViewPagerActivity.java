package com.we.androidwetoutiao.ui.home.newsdetail;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.github.chrisbanes.photoview.PhotoView;
import com.we.androidwetoutiao.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ImageViewPagerActivity extends Activity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.photoV)
    PhotoView photoV;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_img_preview);
        ButterKnife.bind(this);
        setData();
    }

    private void setData() {
        ArrayList<String> imgList = getIntent().getStringArrayListExtra("imgList");
        tvTitle.setText(imgList.get(0));
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        finish();
    }
}
