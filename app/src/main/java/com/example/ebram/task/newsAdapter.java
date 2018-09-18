package com.example.ebram.task;

import android.content.Context;
import android.graphics.Movie;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class newsAdapter extends RecyclerView.Adapter<newsAdapter.NewsViewHolder>{
    private List<News> news;
   private Context context;
   private LayoutInflater layoutInflater;

    public newsAdapter(List<News> news, Context context) {
        this.news = news;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.news_row,parent,false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NewsViewHolder holder, int position) {
         News n=news.get(position);
         holder.title.setText(n.getNewsTitle());
         holder.Date.setText(n.getPostDate());
         holder.likes.setText(n.getLikes());
         holder.views.setText(n.getNumofViews());
    }

    @Override
    public int getItemCount() {
        return (news.size());
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder
    {
        private TextView title,Date,likes,views;
        private ImageView image;

        public NewsViewHolder(View itemView) {
            super(itemView);
            title =itemView.findViewById(R.id.tvTitle);
            Date =itemView.findViewById(R.id.tvDate);
            likes =itemView.findViewById(R.id.tvLikes);
            views =itemView.findViewById(R.id.tvView);
            image =itemView.findViewById(R.id.imNew);
        }
    }
}
