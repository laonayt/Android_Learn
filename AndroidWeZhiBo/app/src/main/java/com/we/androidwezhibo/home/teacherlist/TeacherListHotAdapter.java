package com.we.androidwezhibo.home.teacherlist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

import java.util.List;

public class TeacherListHotAdapter extends RecyclerView.Adapter<TeacherListViewHolder> {
    private List<TeacherListHotBean> mDataList;

    //初始化
    public TeacherListHotAdapter(List<TeacherListHotBean> dataList) {
        mDataList = dataList;
    }

    @NonNull
    @Override
    public TeacherListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_teacher_list,parent,false);
        return new TeacherListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TeacherListViewHolder holder, int position) {
        TeacherListHotBean beanM = mDataList.get(position);
        holder.tvName.setText(beanM.teacherName);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
