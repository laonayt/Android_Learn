package com.we.androidwezhibo.home.teacherlist;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwezhibo.R;

public class TeacherListViewHolder extends RecyclerView.ViewHolder {
    public TextView tvName;

    public TeacherListViewHolder(@NonNull View itemView) {
        super(itemView);
        tvName = itemView.findViewById(R.id.tv_name);
    }
}
