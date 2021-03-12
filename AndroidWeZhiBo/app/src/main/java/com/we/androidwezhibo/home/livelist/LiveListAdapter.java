package com.we.androidwezhibo.home.livelist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

import java.util.ArrayList;
import java.util.List;


public class LiveListAdapter  extends RecyclerView.Adapter<LiveListAdapter.LiveListViewHolder> {
    private List<LiveListBean.LiveListItem> mDataList;
    private ItemClickListener mItemClickListener;

    //初始化方法
    public LiveListAdapter(List<LiveListBean.LiveListItem> dataList) {
        if (dataList == null){
            this.mDataList = new ArrayList<>();
        }
        this.mDataList = dataList;
    }

    public void setItemClickListener(ItemClickListener listener) {
        mItemClickListener = listener;
    }

    @NonNull
    @Override
    public LiveListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_live_list, parent, false);
        return new LiveListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LiveListViewHolder holder, int position) {
        LiveListBean.LiveListItem itemM = mDataList.get(position);
        holder.title_tv.setText(itemM.name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(itemM);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class LiveListViewHolder extends RecyclerView.ViewHolder {
        public TextView title_tv;

        public LiveListViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface ItemClickListener {
        void onItemClick(LiveListBean.LiveListItem item);
    }
}
