package com.we.androidwetoutiao.ui.home;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.we.androidwetoutiao.ui.home.newslist.NewsListFragment;

import java.util.ArrayList;
import java.util.List;

public class HomeChannelAdapter extends FragmentStatePagerAdapter {

    private List<NewsListFragment> mFragments;
    private List<HomeChannelModel> mChannels;

    public HomeChannelAdapter(List<NewsListFragment> fragmentList, List<HomeChannelModel> channelList, @NonNull FragmentManager fm) {
        super(fm);
        mFragments = fragmentList != null ? fragmentList : new ArrayList<NewsListFragment>();
        mChannels = channelList != null ? channelList : new ArrayList<HomeChannelModel>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragments.get(position);
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannels.get(position).title;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
