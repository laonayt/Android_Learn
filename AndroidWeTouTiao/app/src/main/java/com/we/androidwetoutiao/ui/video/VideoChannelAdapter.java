package com.we.androidwetoutiao.ui.video;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.we.androidwetoutiao.ui.video.videolist.VideoListFragment;

import java.util.ArrayList;
import java.util.List;

public class VideoChannelAdapter extends FragmentStatePagerAdapter {
    private List<VideoChannelModel> mChannelModelList;
    private List<VideoListFragment> mFragmentList;

    public VideoChannelAdapter(List<VideoChannelModel> channelModelList, List<VideoListFragment> fragmentList, @NonNull FragmentManager fm) {
        super(fm);
        mChannelModelList = channelModelList != null ? channelModelList : new ArrayList<>();
        mFragmentList = fragmentList != null ? fragmentList : new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return mChannelModelList.get(position).title;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return POSITION_NONE;
    }
}
