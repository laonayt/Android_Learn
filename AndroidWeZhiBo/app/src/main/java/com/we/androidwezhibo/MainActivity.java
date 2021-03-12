package com.we.androidwezhibo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.chaychan.library.BottomBarLayout;
import com.we.androidwezhibo.common.NoScrollViewPager;
import com.we.androidwezhibo.home.HomeFragment;
import com.we.androidwezhibo.live.LiveFragment;
import com.we.androidwezhibo.mine.MineFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.vp_content)
    NoScrollViewPager vpContent;
    @BindView(R.id.bottom_bar)
    BottomBarLayout bottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
    }

    private void initData() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(new HomeFragment());
        fragmentList.add(new LiveFragment());
        fragmentList.add(new MineFragment());

        MainTabAdapter tabAdapter = new MainTabAdapter(fragmentList, getSupportFragmentManager());
        vpContent.setAdapter(tabAdapter);
        vpContent.setOffscreenPageLimit(fragmentList.size());

        bottomBar.setViewPager(vpContent);
    }
}