package com.we.androidwetoutiao.ui.video;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.ui.video.videolist.VideoListFragment;
import com.we.androidwetoutiao.utils.Constant;
import com.we.androidwetoutiao.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

public class VideoFragment extends Fragment {
    @BindView(R.id.tab_channel)
    ColorTrackTabLayout mTabChannel;
    @BindView(R.id.iv_operation)
    ImageView mIvOperation;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    List<VideoChannelModel> selectChannels = new ArrayList<>();
    List<VideoListFragment> fragmentList = new ArrayList<>();
    VideoChannelAdapter channelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video, container, false);
        ButterKnife.bind(this,view);

        initData();

        return view;
    }

    private void initData() {
        String[] channels = getResources().getStringArray(R.array.channel_video);
        String[] channelsCodes = getResources().getStringArray(R.array.channel_code_video);
        for (int i = 0; i < channelsCodes.length; i++){
            VideoChannelModel model = new VideoChannelModel(channels[i],channelsCodes[i]);
            selectChannels.add(model);
        }

        for (VideoChannelModel model : selectChannels) {
            VideoListFragment fragment = new VideoListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE,model.channelCode);
            fragment.setArguments(bundle);
            fragmentList.add(fragment);
        }

        channelAdapter = new VideoChannelAdapter(selectChannels, fragmentList, getChildFragmentManager());
        mVpContent.setOffscreenPageLimit(fragmentList.size());
        mVpContent.setAdapter(channelAdapter);

        mTabChannel.setTabPaddingLeftAndRight(UIUtils.dip2Px(10), UIUtils.dip2Px(10));
        mTabChannel.setupWithViewPager(mVpContent);
    }
}
