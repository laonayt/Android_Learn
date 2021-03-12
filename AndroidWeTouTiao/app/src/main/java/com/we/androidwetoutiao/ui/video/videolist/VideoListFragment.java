package com.we.androidwetoutiao.ui.video.videolist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwetoutiao.R;
import com.we.androidwetoutiao.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoListFragment extends Fragment {
    @BindView(R.id.rv_videos)
    RecyclerView mRvVideos;
    private List<VideoListModel> videoListModels = new ArrayList<>();
    private VideoListAdapter videoListAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);

        ButterKnife.bind(this,view);
        initData();
        requestData();

        return view;
    }

    private void initData() {
        //设置数据源
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        mRvVideos.setLayoutManager(linearLayoutManager);

        videoListAdapter = new VideoListAdapter(getContext(), videoListModels);
        mRvVideos.setAdapter(videoListAdapter);
    }

    private void requestData() {
        String channelCode = getArguments().getString(Constant.CHANNEL_CODE);

    }
}
