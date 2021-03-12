package com.we.androidwetoutiao.ui.home;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.ui.home.newslist.NewsListFragment;
import com.we.androidwetoutiao.utils.Constant;
import com.we.androidwetoutiao.utils.PreUtils;
import com.we.androidwetoutiao.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.weyye.library.colortrackview.ColorTrackTabLayout;

public class HomeFragment extends Fragment {
    @BindView(R.id.tab_channel)
    ColorTrackTabLayout mTabChannel;
    @BindView(R.id.iv_operation)
    ImageView ivAddChannel;
    @BindView(R.id.vp_content)
    ViewPager mVpContent;

    private List<HomeChannelModel> mSelectedChannels = new ArrayList<>();
    private List<NewsListFragment> mChannelFragments = new ArrayList<>();
    private HomeChannelAdapter mHomeChannelAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,view);

        initData();

        return view;
    }

    private void initData() {
        //1、
        String selectedChannelJson = PreUtils.getString(Constant.SELECTED_CHANNEL_JSON, "");
        String unselectChannel = PreUtils.getString(Constant.UNSELECTED_CHANNEL_JSON, "");

        if (TextUtils.isEmpty(selectedChannelJson) || TextUtils.isEmpty(unselectChannel)) {
            String[] channels = getResources().getStringArray(R.array.channel);
            String[] channelCodes = getResources().getStringArray(R.array.channel_code);
            for (int i = 0; i < channelCodes.length; i++) {
                String title = channels[i];
                String code = channelCodes[i];
                mSelectedChannels.add(new HomeChannelModel(title, code));
            }
        }

        //2、
        for (HomeChannelModel homeChannel : mSelectedChannels) {
            NewsListFragment newsFragment = new NewsListFragment();
            Bundle bundle = new Bundle();
            bundle.putString(Constant.CHANNEL_CODE, homeChannel.channelCode);
            newsFragment.setArguments(bundle);
            mChannelFragments.add(newsFragment);
        }

        //3、
        mHomeChannelAdapter = new HomeChannelAdapter(mChannelFragments, mSelectedChannels, getChildFragmentManager());
        mVpContent.setAdapter(mHomeChannelAdapter);
        mVpContent.setOffscreenPageLimit(mChannelFragments.size());

        mTabChannel.setTabPaddingLeftAndRight(UIUtils.dip2Px(10), UIUtils.dip2Px(10));
        mTabChannel.setupWithViewPager(mVpContent);
    }

    @OnClick(R.id.iv_operation)
    public void onClick(View view) {
        ChannelDialogFragment channelDialogFragment = new ChannelDialogFragment();
        channelDialogFragment.show(getFragmentManager(),"Channel");
    }
}
