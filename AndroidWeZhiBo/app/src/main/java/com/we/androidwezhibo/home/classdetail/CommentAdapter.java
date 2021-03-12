package com.we.androidwezhibo.home.classdetail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    private List<CommentListBean.CommentListItem> mDataList;

    //初始化方法
    public CommentAdapter(List<CommentListBean.CommentListItem> dataList) {
        if (dataList == null) {
            mDataList = new ArrayList<>();
        } else {
            mDataList = dataList;
        }
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comment_list,parent,false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {
        CommentListBean.CommentListItem itemM = mDataList.get(position);
        holder.tv_content.setText(itemM.content);
        holder.tv_user.setText(itemM.createuser);
        holder.tv_time.setText(itemM.createdate);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        public TextView tv_content;
        public TextView tv_user;
        public TextView tv_time;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_content = itemView.findViewById(R.id.tv_content);
            tv_user = itemView.findViewById(R.id.tv_user);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
