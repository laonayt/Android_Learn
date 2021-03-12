package com.we.androidwezhibo.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

import java.util.List;

public class HomeListAdapter extends RecyclerView.Adapter<HomeListAdapter.HomeListViewHolder> {
    private List<HomeListBean.HomeListItem> mDataList;

    //初始化方法
    public HomeListAdapter(List<HomeListBean.HomeListItem> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public HomeListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_home_list,parent,false);
        return new HomeListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeListViewHolder holder, int position) {
        HomeListBean.HomeListItem itemM = mDataList.get(position);
        holder.tv_author.setText(itemM.author);
        holder.tv_time.setText(itemM.createDate);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class HomeListViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_author;
        public TextView tv_time;

        public HomeListViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_author = itemView.findViewById(R.id.tv_author);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }

}
