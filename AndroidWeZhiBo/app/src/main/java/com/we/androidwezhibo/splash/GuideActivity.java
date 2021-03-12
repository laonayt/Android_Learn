package com.we.androidwezhibo.splash;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.we.androidwezhibo.R;
import com.we.androidwezhibo.login.LoginActivity;
import com.we.androidwezhibo.utils.SpTool;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class GuideActivity extends AppCompatActivity {
    @BindView(R.id.vp_content)
    ViewPager vpContent;

    private final int[] ids = {R.layout.page_guide_1, R.layout.page_guide_2, R.layout.page_guide_3};
    @BindView(R.id.bt_start)
    Button btStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < ids.length; i++) {
            View view = View.inflate(this, ids[i], null);
            views.add(view);
        }
        vpContent.setAdapter(new GuideAdapter(views));
        vpContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position == ids.length - 1) {
                    btStart.setVisibility(View.VISIBLE);
                } else {
                    btStart.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @OnClick(R.id.bt_start)
    public void onViewClicked() {
        Intent intent = new Intent(GuideActivity.this, LoginActivity.class);
        startActivity(intent);
        SpTool.saveBool(true,"notFirst");
//        finish();
    }
}