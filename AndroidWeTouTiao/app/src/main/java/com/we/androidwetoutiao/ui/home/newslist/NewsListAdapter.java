package com.we.androidwetoutiao.ui.home.newslist;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.we.androidwetoutiao.R;

import java.util.List;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.NewsViewHolder> {
    private Context context;
    private List<NewsListModel> dataList;
    private ItemClickListener mListener;

    public NewsListAdapter(Context context, List<NewsListModel> newsList) {
        this.context = context;
        this.dataList = newsList;
    }

    public void setClickListener(ItemClickListener listener) {
        this.mListener = listener;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_news_list, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsListModel model = dataList.get(position);
        holder.title_tv.setText(model.title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onItemClick(holder, model, position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.dataList == null ? 0 : this.dataList.size();
    }



    public class NewsViewHolder extends RecyclerView.ViewHolder {
        public TextView title_tv;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            title_tv = itemView.findViewById(R.id.tv_title);
        }
    }

    public interface ItemClickListener {
        void onItemClick(NewsViewHolder holder, NewsListModel model, int position);
    }
}
