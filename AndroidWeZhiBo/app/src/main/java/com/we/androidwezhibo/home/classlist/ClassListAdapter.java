package com.we.androidwezhibo.home.classlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

import java.util.ArrayList;
import java.util.List;

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ClassListViewHolder> {
    private List<ClassListBean.ClassListItem> mDataList;
    private ItemClickListener mItemClickListener;

    public ClassListAdapter(List<ClassListBean.ClassListItem> dataList) {
        if (dataList == null) {
            this.mDataList = new ArrayList<>();
        }
        this.mDataList = dataList;
    }

    public void setmItemClickListener(ItemClickListener mItemClickListener) {
        this.mItemClickListener = mItemClickListener;
    }

    @NonNull
    @Override
    public ClassListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class_list, parent, false);
        return new ClassListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassListViewHolder holder, int position) {
        ClassListBean.ClassListItem itemBean = mDataList.get(position);
        holder.resource_tv.setText(itemBean.resourceName);
        holder.author_tv.setText(itemBean.author);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemClick(itemBean);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.mDataList.size();
    }

    public class ClassListViewHolder extends RecyclerView.ViewHolder {
        public TextView resource_tv;
        public TextView author_tv;

        public ClassListViewHolder(@NonNull View itemView) {
            super(itemView);
            resource_tv = itemView.findViewById(R.id.tv_resource);
            author_tv = itemView.findViewById(R.id.tv_author);
        }
    }

    public interface ItemClickListener {
        void onItemClick(ClassListBean.ClassListItem item);
    }
}
